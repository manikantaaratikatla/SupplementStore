package com.example.SupplyStore.repository;

import com.example.SupplyStore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem findByCustomerIdAndItemId(int customerId, int itemId);
    List<CartItem> findByCustomerId(int customerId);
    void deleteByCustomerId(int customerId); // Added for clearing cart
}
