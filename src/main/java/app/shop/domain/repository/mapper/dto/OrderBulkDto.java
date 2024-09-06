package app.shop.domain.repository.mapper.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderBulkDto{
    private Long id;
    private String name;
    private String address;
    
    @Builder
    public OrderBulkDto(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
