package app.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.shop.controller.dto.CreateOrderDto;
import app.shop.controller.dto.OrderItemDto;
import app.shop.core.BaseController;
import app.shop.domain.service.OrderService;
import app.shop.utils.excel.Column;
import app.shop.utils.excel.ExcelManager;
import app.shop.utils.validation.Validator;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @GetMapping("/ping")
    public String ping() {
        Validator.require(false, () -> new IllegalArgumentException("12321"));
        // Product p = productService.findById(1L);
        // logger.info("{}", p);
        return "pong";
    }

    @PostMapping()
    public String createOrder(@RequestBody CreateOrderDto createOrderDto) {
        // orderService.createOrder();
        logger.info("{}", createOrderDto);
        orderService.createOrder(createOrderDto);
        return "pong";
    }

    @PostMapping(path = "upload/excel")
    public void upload(@RequestParam("file") MultipartFile file) {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("상품명", "key"));
        columns.add(new Column("상품별 주문 수량", "count"));
        
        ExcelManager excelManager = new ExcelManager(OrderItemDto.class, columns);
        List<?> test = excelManager
            .upload(file)
            .parse();
        logger.info("gkgkgkgkg");
        logger.info("test === {}", test);
    	// excelManageServiceImpl.upload(file);
    }
    
}