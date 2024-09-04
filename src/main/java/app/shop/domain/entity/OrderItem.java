package app.shop.domain.entity;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import app.shop.core.BaseEntity;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity(name = "T_ORDER_ITEM")
public class OrderItem extends BaseEntity {

    @Column(nullable = false)
    private Long count;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    private Order order;
    
    @Builder
    public OrderItem(Long count, Item item) {
        this.count = count;
        this.item = item;
    }
}
