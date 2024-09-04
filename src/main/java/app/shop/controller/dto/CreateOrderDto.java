package app.shop.controller.dto;

import java.util.List;

import app.shop.domain.entity.Order;
import lombok.Data;

@Data
public class CreateOrderDto {
    // 상품명, 상품 PK, 상품별 주문 수량, 주문자명, 주문자 주소
    private List<OrderItemDto> orderItems;
    private OrdererDto orderer;

    public Order toEntity() {
        return Order.builder()
                .orderItems(
                    orderItems
                        .stream()
                        .map(OrderItemDto::toEntity)
                        .toList()
                )
                .orderer(orderer.toEntity())
                .build();
    }
}
