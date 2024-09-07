package app.shop.domain.service;

import app.shop.domain.vo.*;
import app.shop.domain.repository.OrderRepository;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import app.shop.controller.dto.CreateOrderDto;
import app.shop.controller.dto.OrderItemDto;
import app.shop.controller.dto.OrdererDto;

@DataJpaTest
@ExtendWith({MockitoExtension.class})
// @ExtendWith({SpringExtension.class, MockitoExtension.class})
@Import({OrderService.class})
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @MockBean
    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createOrderTest() {
        OrderItemDto orderItemDto = 
            OrderItemDto.of(
                ItemName.of("상품명"), OrderItemCount.of(10L)
            );
        assertEquals(orderItemDto, OrderItemDto.of(
            ItemName.of("상품명"), OrderItemCount.of(10L)
        ));

        List<OrderItemDto> orderItems = new ArrayList<>();
        orderItems.add(orderItemDto);

        OrdererDto ordererDto = OrdererDto.of(OrdererName.of("kim"), Address.of("test"));
        assertEquals(ordererDto, OrdererDto.of(OrdererName.of("kim"), Address.of("test")));

        Mockito.when(orderService.createOrder(new CreateOrderDto.Request(orderItems, ordererDto))).thenReturn(new CreateOrderDto.Response(true));

        CreateOrderDto.Response result = orderService.createOrder(new CreateOrderDto.Request(orderItems, ordererDto));

        CreateOrderDto.Response expected = new CreateOrderDto.Response(true);
        Assertions.assertEquals(result, expected);
    }
}
