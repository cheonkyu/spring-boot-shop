package app.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.shop.domain.entity.Orderer;

public interface OrdererRepository extends JpaRepository<Orderer, Long> {

}