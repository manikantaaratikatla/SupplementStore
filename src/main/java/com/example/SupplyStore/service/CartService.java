package com.example.SupplyStore.service;

import com.example.SupplyStore.entity.CartItem;
import com.example.SupplyStore.entity.Item;
import com.example.SupplyStore.repository.CartItemRepository;
import com.example.SupplyStore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    // Add or update item in cart
    public void addToCart(int customerId, int itemId, int quantity) {
        CartItem existingItem = cartItemRepository.findByCustomerIdAndItemId(customerId, itemId);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCustomerId(customerId);
            newItem.setItemId(itemId);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
    }

    // Get all cart items for a customer
    public List<CartItem> getCartItemsByCustomerId(Integer customerId) {
        return cartItemRepository.findByCustomerId(customerId);
    }

    // Remove item from cart
    public void removeItemFromCart(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // Clear all items from a customer's cart
    @Transactional
    public void clearCart(int customerId) {
        cartItemRepository.deleteByCustomerId(customerId);
    }

    // Calculate total amount of items in cart
    public double calculateCartTotal(List<CartItem> cartItems) {
        double total = 0.0;
        for (CartItem cartItem : cartItems) {
            Item item = itemRepository.findById(cartItem.getItemId());
            if (item != null) {
                total += item.getPrice() * cartItem.getQuantity();
            }
        }
        return total;
    }
}
