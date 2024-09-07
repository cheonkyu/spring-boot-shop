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
@Entity(name = "T_ITEM")
@SQLDelete(sql = "UPDATE T_ITEM SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Item extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ITEM_ID")
    @ToString.Exclude
    private OrderItem orderItems;
    
    @Builder
    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
