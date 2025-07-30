package com.example.SupplyStore.repository;

import com.example.SupplyStore.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByNameContainingIgnoreCase(String name);
    Item findById(int id); // Added for fetching item by ID
}
