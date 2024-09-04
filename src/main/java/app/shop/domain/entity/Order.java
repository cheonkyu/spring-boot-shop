package app.shop.domain.entity;

import java.util.List;

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
public class Order extends BaseEntity {

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> orderItems;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ORDERER_ID")
    private Orderer orderer;

    @Builder
    public Order(List<OrderItem> orderItems, Orderer orderer) {
        this.orderItems = orderItems;
        this.orderer = orderer;
    }
}
