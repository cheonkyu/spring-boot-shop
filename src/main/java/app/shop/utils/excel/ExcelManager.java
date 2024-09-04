package app.shop.utils.excel;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import app.shop.controller.dto.OrderItemDto;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FilenameUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JavaType;

public class ExcelManager {

  private ObjectMapper mapper = new ObjectMapper();
  private List<Map<String, String>> data = new ArrayList<>();
  private Class<?> clazz;
  private List<Column> columns;

  public ExcelManager(final Class<?> clazz, final List<Column> columns) {
    this.clazz = clazz;
    this.columns = columns;
  }


  private String getCellValue(final Cell cell) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    String value = "";
      // 타입별로 내용 읽기
      switch (cell.getCellType()) {
          case FORMULA:
            value = cell.getCellFormula();
            break;
          case NUMERIC:
            if (HSSFDateUtil.isCellDateFormatted(cell)) { // 숫자- 날짜 타입이다.
                  value = formatter.format(cell.getDateCellValue());
            } else {
                  double numericCellValue = cell.getNumericCellValue();
                  value = String.valueOf(numericCellValue);
                  if (numericCellValue == Math.rint(numericCellValue)) {
                      value = String.valueOf((int) numericCellValue);
                  } else {
                      value = String.valueOf(numericCellValue);
                  }
            }
            break;
          case STRING:
            value = cell.getStringCellValue() + "";
            break;
          case BLANK:
            value = cell.getBooleanCellValue() + "";
            break;
          case ERROR:
            value = cell.getErrorCellValue() + "";
            break;
          default:
            value = cell.getStringCellValue();
            break;
      }
      return value;
  }

	public ExcelManager upload(MultipartFile file) {
        final String fileExtsn = FilenameUtils.getExtension(file.getOriginalFilename()); // 파일 Original 이름 불러오기 ex) 전문가.xlsx
        
        Workbook workbook = null;
        
        try {
            
            // 엑셀 97 - 2003 까지는 HSSF(xls),  엑셀 2007 이상은 XSSF(xlsx)
            if ("xls".equals(fileExtsn)) {
            	workbook = new HSSFWorkbook(file.getInputStream());
            } else {
              workbook = new XSSFWorkbook(file.getInputStream());
            }
        	
            // 엑셀파일에서 첫번째 시트 불러오기
            Sheet worksheet = workbook.getSheetAt(0);
            
            final int rowNum = worksheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rowNum; i++) {
              Row row = worksheet.getRow(i);

              if (row != null) {
                final int columnSize = this.columns.size();
                final Map<String, String> cellData = new HashMap<>();
                for(int j = 0; j < columnSize; j++) {
                  final Cell cell = row.getCell(j + 1);
                  if (cell == null) {
                    continue;
                  }

                  final String value = this.getCellValue(cell);
                  final Column column = this.columns.get(j);


                  cellData.put(column.getKey(), value);
                }
                data.add(cellData);
              }
            }
            
            workbook.close();
            return this;
            
        } catch (Exception e) {
          System.out.println(e.toString());
          // throw new BizException(e);
          return this;

        } 
    }

    public List<?> parse() {
      final JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, this.clazz);
      List<?> result = mapper.convertValue(this.data, type);
      return result;
    }
}