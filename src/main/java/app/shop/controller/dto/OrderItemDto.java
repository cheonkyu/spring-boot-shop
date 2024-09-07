package app.shop.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import app.shop.controller.dto.binding.OrderItemDtoDeserializer;
import app.shop.domain.entity.Item;
import app.shop.domain.entity.OrderItem;
import app.shop.domain.vo.ItemName;
import app.shop.domain.vo.OrderItemCount;

@JsonDeserialize(using = OrderItemDtoDeserializer.class)
public record OrderItemDto(
    ItemName name,
    OrderItemCount count
) {

    public static OrderItemDto of(ItemName name, OrderItemCount count) {
        return new OrderItemDto(name, count);
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .count(count)
                .item(Item.builder().name(name).build())
                .build();
    }
}
