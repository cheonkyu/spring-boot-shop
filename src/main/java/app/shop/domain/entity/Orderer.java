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
@Entity(name = "T_ORDERER")
public class Orderer extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fullAddress;

    @Column(nullable = false)
    private String address1;

    @Column(nullable = false)
    private String address2;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    private Order order;
    
    @Builder
    public Orderer(String name, String fullAddress, String address1, String address2) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.fullAddress = address1 + address2;
    }
}
