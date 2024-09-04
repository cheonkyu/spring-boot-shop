package app.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.shop.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}