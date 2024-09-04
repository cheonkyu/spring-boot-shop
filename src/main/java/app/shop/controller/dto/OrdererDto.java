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
    private String address1;
    private String address2;

    public Orderer toEntity() {
        return Orderer.builder()
                .name(name)
                .address1(address1)
                .address2(address2)
                .build();
    }
}
