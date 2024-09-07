package app.shop.controller.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.shop.domain.entity.Item;
import app.shop.domain.entity.Order;
import app.shop.domain.entity.OrderItem;
import app.shop.domain.entity.Orderer;
import app.shop.domain.vo.Address;
import app.shop.domain.vo.ItemName;
import app.shop.domain.vo.OrderItemCount;
import app.shop.domain.vo.OrdererName;

public class BulkInsertOrderDto {
    public record Request(
        String itemId,
        String itemName,
        String orderCount,
        String ordererName,
        String ordererAddress
    ) {
        public static Request of(
            String itemId,
            String itemName,
            String orderCount,
            String ordererName,
            String ordererAddress
        ) {
            return new Request(itemId, itemName, orderCount, ordererName, ordererAddress);
        }

        public Order toEntity() {
            final Orderer orderer = Orderer.builder()
                .name(OrdererName.of(ordererName))
                .address(Address.of(ordererAddress))
                .build();

            final List<OrderItem> orderItems = new ArrayList<>();

            final Order order = Order.builder()
                .orderer(orderer)
                .orderItems(orderItems)
                .build();

            final Item item = Item.builder()
                // .id(Long.parseLong(this.itemId))
                .name(ItemName.of(this.itemName))
                .build();

            final OrderItem orderItem = OrderItem.builder()
                .count(OrderItemCount.of(Long.parseLong(this.orderCount)))
                .item(item)
                .build();
            orderItems.add(orderItem);

            for(OrderItem _orderItem : orderItems) {
                _orderItem.setOrder(order);
            }
            order.setOrderItems(orderItems);
           
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
