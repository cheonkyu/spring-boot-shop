package app.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.shop.controller.dto.BulkInsertOrderDto;
import app.shop.controller.dto.CreateOrderDto;
import app.shop.core.BaseController;
import app.shop.domain.service.OrderService;
import app.shop.utils.validation.Validator;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @PostMapping()
    public CreateOrderDto.Response createOrder(@RequestBody CreateOrderDto.Request createOrderDto) {
        logger.info("{}", createOrderDto);
        return orderService.createOrder(createOrderDto);
    }

    @PostMapping(path = "/excel/bulk-insert")
    public BulkInsertOrderDto.Response bulkInsertOrder(@RequestParam("file") MultipartFile file) {
        return orderService.bulkInsertOrder(file);
    }
    
}