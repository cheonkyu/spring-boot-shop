package app.shop.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import app.shop.controller.dto.binding.OrdererDtoDeserializer;
import app.shop.domain.entity.Orderer;
import app.shop.domain.vo.Address;
import app.shop.domain.vo.OrdererName;

@JsonDeserialize(using = OrdererDtoDeserializer.class)
public record OrdererDto(
    OrdererName name,
    Address address
) {
    public static OrdererDto of(OrdererName name, Address address) {
        return new OrdererDto(name, address);
    }
    
    public Orderer toEntity() {
        return Orderer.builder()
                .name(name)
                .address(address)
                .build();
    }
}
