package com.shipshoe.productservice.repository;

import com.shipshoe.productservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
