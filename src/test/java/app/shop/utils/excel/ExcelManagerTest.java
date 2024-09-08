package app.shop.utils.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import app.shop.controller.dto.BulkInsertOrderDto;
import app.shop.controller.dto.CreateOrderDto;

public class ExcelManagerTest {
    @Test
    @DisplayName("엑셀매니저, 파일로부터 데이터 로드")
    public void excelManagerTest() {
        try {
          
            final List<Column> columns = new ArrayList<>();
            columns.add(Column.of("상품명", "itemName"));
            columns.add(Column.of("상품키", "itemId"));
            columns.add(Column.of("상품별 주문 수량", "orderCount"));
            columns.add(Column.of("주문자명", "ordererName"));
            columns.add(Column.of("주문자주소", "ordererAddress"));
        
            final ExcelManager excelManager = new ExcelManager(BulkInsertOrderDto.Request.class, columns);
            MockMultipartFile excelFile = new MockMultipartFile("sample", "sample.xlsx", "application/vnd.ms-excel", new ClassPathResource("sample.xlsx").getInputStream());

            List<Map<String, String>> data = excelManager
                .loadExcel(excelFile)
                .getData();

            final Long mockRowCount = 17L;
            Assertions.assertEquals(data.size(), mockRowCount);
        } catch (IOException io) {
            // Assertions.assertThrows(io, () -> io);
        }
    }
}
