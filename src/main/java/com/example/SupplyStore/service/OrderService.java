package com.example.SupplyStore.service;

import com.example.SupplyStore.entity.CartItem;
import com.example.SupplyStore.entity.Item;
import com.example.SupplyStore.entity.Order;
import com.example.SupplyStore.entity.OrderDetail;
import com.example.SupplyStore.repository.CartItemRepository;
import com.example.SupplyStore.repository.ItemRepository;
import com.example.SupplyStore.repository.OrderDetailRepository;
import com.example.SupplyStore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Order placeOrder(int customerId) {
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cannot place an order with an empty cart.");
        }

        double totalAmount = 0.0;
        for (CartItem cartItem : cartItems) {
            Item item = itemRepository.findById(cartItem.getItemId());
            if (item == null) {
                throw new IllegalStateException("Item with ID " + cartItem.getItemId() + " not found.");
            }
            totalAmount += item.getPrice() * cartItem.getQuantity();
        }

        // Create new order
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        orderRepository.save(order); // Save to get the generated orderId

        // Create order details for each item in the cart
        for (CartItem cartItem : cartItems) {
            Item item = itemRepository.findById(cartItem.getItemId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getOrderId());
            orderDetail.setItemId(cartItem.getItemId());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPriceAtPurchase(item.getPrice()); // Record price at time of purchase
            orderDetailRepository.save(orderDetail);
        }

        // Clear the cart after placing the order
        cartItemRepository.deleteByCustomerId(customerId);

        return order;
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById((long) orderId).orElse(null);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
