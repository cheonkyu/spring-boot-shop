package app.shop.domain.repository.mapper.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemBulkDto{
    private Long id;
    private String name;
    
    @Builder
    public ItemBulkDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
