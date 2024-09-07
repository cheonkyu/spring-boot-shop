package app.shop.domain.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import app.shop.core.BaseEntity;
import app.shop.domain.vo.OrderItemCount;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity(name = "T_ORDER_ITEM")
@SQLDelete(sql = "UPDATE T_ORDER_ITEM SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class OrderItem extends BaseEntity {

    @Column(nullable = false)
    @Embedded
    private OrderItemCount count;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    
    @Builder
    public OrderItem(OrderItemCount count, Item item) {
        this.count = count;
        this.item = item;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
