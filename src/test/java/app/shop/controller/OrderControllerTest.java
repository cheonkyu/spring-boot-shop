package app.shop.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("단 건 주문 API")
    public void createOrderTest() throws Exception{
        mvc.perform(post("/order"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("엑셀 업로드 주문 등록 API")
    public void bulkInsertOrderTest() throws Exception{
        mvc.perform(post("/order/excel/bulk-insert"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}


