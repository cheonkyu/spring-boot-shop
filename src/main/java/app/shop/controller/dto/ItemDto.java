package app.shop.controller.dto;

import app.shop.domain.entity.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ItemDto {
    private String name;

    public Item toEntity() {
        return Item.builder()
                .name(name)
                .build();
    }
}
