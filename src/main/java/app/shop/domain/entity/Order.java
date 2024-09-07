package app.shop.domain.entity;

import java.util.List;
import java.util.ArrayList;

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

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ORDERER_ID")
    private Orderer orderer;

    @Builder
    public Order(List<OrderItem> orderItems, Orderer orderer) {
        this.orderItems = orderItems;
        this.orderer = orderer;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
