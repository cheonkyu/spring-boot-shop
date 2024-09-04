package app.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.shop.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}