package app.shop.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import app.shop.core.BaseEntity;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity(name = "T_ITEM")
public class Item extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "ORDER_ITEM_ID")
    @ToString.Exclude
    // @JoinColumn(name = "ORDER_ITEM_ID", insertable = false, updatable = false)
    private OrderItem orderItems;
    
    @Builder
    public Item(String name) {
        this.name = name;
    }
}
