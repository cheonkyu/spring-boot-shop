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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/order")
@Tag(name = "주문(Order)", description = "API")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @Operation(summary = "단건 주문 등록")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CreateOrderDto.Response.class))),
    })
    @PostMapping()
    public CreateOrderDto.Response createOrder(@RequestBody CreateOrderDto.Request createOrderDto) {
        logger.info("{}", createOrderDto);
        return orderService.createOrder(createOrderDto);
    }

    @Operation(summary = "엑셀 다건 주문 등록")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BulkInsertOrderDto.Response.class))),
    })
    @PostMapping(path = "/excel/bulk-insert")
    public BulkInsertOrderDto.Response bulkInsertOrder(@RequestParam("file") MultipartFile file) {
        return orderService.bulkInsertOrder(file);
    }
    
}