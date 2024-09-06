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
import app.shop.domain.entity.Order;
import app.shop.domain.repository.OrderRepository;
import app.shop.domain.repository.mapper.BulkInsertMapper;
import app.shop.domain.repository.mapper.dto.ItemBulkDto;
import app.shop.domain.repository.mapper.dto.OrdererBulkDto;
import app.shop.utils.excel.Column;
import app.shop.utils.excel.ExcelManager;

@RequiredArgsConstructor
@Service
public class OrderService extends BaseService {
    private final OrderRepository orderRepository;
    private final SqlSessionFactory sqlSessionFactory;

    @Transactional
    public CreateOrderDto.Response createOrder(CreateOrderDto.Request createOrderDto) {
        Order order = createOrderDto.toEntity();
        orderRepository.save(order);

        final boolean isSuccess = true;
        final CreateOrderDto.Response result = CreateOrderDto.response(isSuccess);
        return result;
    }

    public BulkInsertOrderDto.Response bulkInsertOrder(MultipartFile file) {
        final List<Column> columns = new ArrayList<>();
        columns.add(new Column("상품명", "itemName"));
        columns.add(new Column("상품키", "itemId"));
        columns.add(new Column("상품별 주문 수량", "orderCount"));
        columns.add(new Column("주문자명", "ordererName"));
        columns.add(new Column("주문자주소", "ordererAddress"));
        
        final ExcelManager excelManager = new ExcelManager(BulkInsertOrderDto.Request.class, columns);
        final List<BulkInsertOrderDto.Request> rows = excelManager
            .loadExcel(file)
            .parse()
            .stream()
            .map(x -> (BulkInsertOrderDto.Request)x)
            .collect(Collectors.toList());

        final List<ItemBulkDto> items = rows
            .stream()
            .map(x -> {
                return ItemBulkDto
                    .builder()
                    .id(Long.parseLong(x.itemId()))
                    .name(x.itemName())
                    .build();
            })
            .collect(Collectors.toList());

        final List<OrdererBulkDto> orderers = rows
            .stream()
            .map(x -> {
                return OrdererBulkDto
                    .builder()
                    .name(x.ordererName())
                    .address(x.ordererAddress())
                    .build();
            })
            .collect(Collectors.toList());

        final boolean isSuccess = bulkInsertData(items, orderers);
        final BulkInsertOrderDto.Response result = BulkInsertOrderDto.response(isSuccess);
        return result;
    }

    private boolean bulkInsertData(List<ItemBulkDto> items, List<OrdererBulkDto> orderers) {
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
