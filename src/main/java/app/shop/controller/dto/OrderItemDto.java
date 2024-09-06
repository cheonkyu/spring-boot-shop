package app.shop.controller.dto;

import app.shop.domain.entity.Item;
import app.shop.domain.entity.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderItemDto {
    private String name;
    private Long count;

    public OrderItem toEntity() {
        return OrderItem.builder()
                .count(count)
                .item(Item.builder().name(name).build())
                .build();
    }
}
