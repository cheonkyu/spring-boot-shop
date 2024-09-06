package app.shop.controller.dto;

import app.shop.domain.entity.Orderer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrdererDto {
    private String name;
    private String address;

    public Orderer toEntity() {
        return Orderer.builder()
                .name(name)
                .address(address)
                .build();
    }
}
