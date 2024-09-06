package app.shop.domain.entity;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import app.shop.core.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity(name = "T_ORDER")
@SQLDelete(sql = "UPDATE T_ORDER SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Order extends BaseEntity {

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> orderItems;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERER_ID")
    private Orderer orderer;

    @Builder
    public Order(List<OrderItem> orderItems, Orderer orderer) {
        this.orderItems = orderItems;
        this.orderer = orderer;
    }
}
