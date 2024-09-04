package app.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.shop.domain.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}