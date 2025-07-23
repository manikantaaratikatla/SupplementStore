package com.example.SupplyStore.service;

import com.example.SupplyStore.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final Map<Long, CartItem> cartItems = new HashMap<>();

    public void addToCart(CartItem item) {
        cartItems.merge(item.getId(), item, (oldItem, newItem) -> {
            oldItem.setQuantity(oldItem.getQuantity() + newItem.getQuantity());
            return oldItem;
        });
    }

    public Collection<CartItem> getCartItems() {
        return cartItems.values();
    }

    public void clearCart() {
        cartItems.clear();
    }
}
