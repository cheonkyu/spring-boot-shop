package app.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.shop.domain.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}