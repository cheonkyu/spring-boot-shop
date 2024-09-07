package app.shop.controller.dto;

import java.util.List;

import app.shop.domain.entity.Order;
import app.shop.domain.entity.OrderItem;

public class CreateOrderDto{
    public record Request(
        // 상품명, 상품 PK, 상품별 주문 수량, 주문자명, 주문자 주소
        List<OrderItemDto> items,
        OrdererDto orderer
    ){
        public Order toEntity() {
            Order order = Order
                .builder()
                .orderer(orderer.toEntity())
                .build();
            List<OrderItem> orderItems = items
                    .stream()
                    .map(OrderItemDto::toEntity)
                    .toList();
            order.setOrderItems(orderItems);
            for(OrderItem orderItem: orderItems) {
                orderItem.setOrder(order);
            }
            return order;
        }
    }

    public record Response(Boolean isSuccess) {
        public Response(Boolean isSuccess) {
            this.isSuccess = isSuccess;
        }
    }

    public static Response response(Boolean isSuccess) {
        return new Response(true);
    }

}
