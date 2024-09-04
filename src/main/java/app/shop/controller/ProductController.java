package app.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.shop.controller.dto.ProductDto;
import app.shop.core.BaseController;
import app.shop.domain.entity.Product;
import app.shop.domain.service.ProductService;
import app.shop.utils.excel.Column;
import app.shop.utils.excel.ExcelManager;
import app.shop.utils.validation.Validator;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ProductController extends BaseController {

    private final ProductService productService;

    @GetMapping("/ping")
    public String ping() {
        Validator.require(false, () -> new IllegalArgumentException("12321"));
        // Product p = productService.findById(1L);
        // logger.info("{}", p);
        return "pong";
    }

    @GetMapping("/ping1")
    public String ping1() {
        // productService
        return "pong";
    }

    @PostMapping(path = "upload/excel")
    public void upload(@RequestParam("file") MultipartFile file) {
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("상품명", "key"));
        columns.add(new Column("상품별 주문 수량", "count"));
        
        ExcelManager excelManager = new ExcelManager(ProductDto.class, columns);
        List<?> test = excelManager
            .upload(file)
            .parse();
        logger.info("gkgkgkgkg");
        logger.info("test === {}", test);
    	// excelManageServiceImpl.upload(file);
    }
    
}