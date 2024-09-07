package app.shop.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import app.shop.core.BaseEntity;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity(name = "T_ORDERER")
@SQLDelete(sql = "UPDATE T_ORDERER SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Orderer extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    
    @Builder
    public Orderer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
