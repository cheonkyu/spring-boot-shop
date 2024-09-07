package app.shop.domain.service;

import lombok.RequiredArgsConstructor;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import app.shop.controller.dto.BulkInsertOrderDto;
import app.shop.controller.dto.CreateOrderDto;
import app.shop.core.BaseService;
import app.shop.core.lock.RedissonLock;
import app.shop.domain.entity.Order;
import app.shop.domain.repository.OrderRepository;
import app.shop.domain.repository.mapper.BulkInsertMapper;
import app.shop.domain.repository.mapper.dto.ItemBulkDto;
import app.shop.domain.repository.mapper.dto.OrdererBulkDto;
import app.shop.utils.excel.Column;
import app.shop.utils.excel.ExcelManager;
import app.shop.utils.validation.Validator;

@RequiredArgsConstructor
@Service
public class OrderService extends BaseService {
    private final OrderRepository orderRepository;
    private final SqlSessionFactory sqlSessionFactory;

    private final String LOCK = "order";

    @Transactional
    public CreateOrderDto.Response createOrder(CreateOrderDto.Request createOrderDto) {
        Validator.require(createOrderDto != null, () -> "잠시 후 다시 시도해주세요.");

        try {
            Order order = createOrderDto.toEntity();
            order = orderRepository.save(order);
            
            final boolean isSuccess = true;
            final CreateOrderDto.Response result = CreateOrderDto.response(isSuccess);
            return result;
        } catch(Exception e) {
            logger.error("{}", e);
            return CreateOrderDto.response(false);
        }
    }

    public BulkInsertOrderDto.Response bulkInsertOrder(MultipartFile file) {
        final List<Column> columns = new ArrayList<>();
        columns.add(Column.of("상품명", "itemName"));
        columns.add(Column.of("상품키", "itemId"));
        columns.add(Column.of("상품별 주문 수량", "orderCount"));
        columns.add(Column.of("주문자명", "ordererName"));
        columns.add(Column.of("주문자주소", "ordererAddress"));
        
        final ExcelManager excelManager = new ExcelManager(BulkInsertOrderDto.Request.class, columns);
        final List<BulkInsertOrderDto.Request> rows = excelManager
            .loadExcel(file)
            .parse()
            .stream()
            .map(x -> (BulkInsertOrderDto.Request)x)
            .collect(Collectors.toList());

        logger.info("rows: {}", rows);
        final List<Order> orders = rows
            .stream()
            .map(x -> x.toEntity())
            .collect(Collectors.toList());

        final boolean isSuccess = bulkInsertData(orders);
        final BulkInsertOrderDto.Response result = BulkInsertOrderDto.response(isSuccess);
        return result;
    }

    @RedissonLock(value = LOCK)
    private boolean bulkInsertData( List<Order> orders) {
        orderRepository.saveAll(orders);
        return true;
    }

    @RedissonLock(value = LOCK)
    private boolean bulkInsertRawQueryData(List<ItemBulkDto> items, List<OrdererBulkDto> orderers) {
        // 배치로 여러 건 동시에 등록
        try(final SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            final BulkInsertMapper mapper = sqlSession.getMapper(BulkInsertMapper.class);

            // 1. 상품 등록 (중복되면 에러, 트랜젝션 종료)
            for (final ItemBulkDto item : items) {
                // 아이템 여부 확인
                mapper.insertItem(item);
            }

            // 2. 주문자 정보 등록
            for (final OrdererBulkDto orderer : orderers) {
                mapper.insertOrderer(orderer);
                // logger.info("{}", orderer);
                // logger.info("{}", orderer.getId());
            }

            sqlSession.flushStatements();
            sqlSession.commit();
        } catch(Exception e) {
            logger.error("{}", e);
            return false;
        }
        return true;
    }
}
