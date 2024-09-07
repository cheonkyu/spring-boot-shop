package app.shop.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import app.shop.core.BaseEntity;
import app.shop.domain.vo.Address;
import app.shop.domain.vo.OrdererName;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity(name = "T_ORDERER")
@SQLDelete(sql = "UPDATE T_ORDERER SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Orderer extends BaseEntity {

    @Embedded
    @Column(nullable = false)
    private OrdererName name;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    
    @Builder
    public Orderer(OrdererName name, Address address) {
        this.name = name;
        this.address = address;
    }
}
