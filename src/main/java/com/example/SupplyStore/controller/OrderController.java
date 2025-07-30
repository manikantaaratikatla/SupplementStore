package com.example.SupplyStore.controller;

import com.example.SupplyStore.entity.Customer;
import com.example.SupplyStore.entity.Item;
import com.example.SupplyStore.entity.Order;
import com.example.SupplyStore.entity.OrderDetail;
import com.example.SupplyStore.repository.ItemRepository;
import com.example.SupplyStore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemRepository itemRepository; // To get item details for display

    @PostMapping("/placeOrder")
    public String placeOrder(HttpSession session, Model model) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        try {
            Order order = orderService.placeOrder(customerId);
            model.addAttribute("order", order);
            model.addAttribute("message", "Order placed successfully! Order ID: " + order.getOrderId());
            return "redirect:/orders"; // Redirect to order history
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "cart"; // Stay on cart page with error
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to place order due to an unexpected error.");
            return "cart";
        }
    }

    @GetMapping("/orders")
    public String viewPreviousOrders(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/login";
        }

        List<Order> orders = orderService.getOrdersByCustomerId(customer.getCustomerId());
        model.addAttribute("orders", orders);

        // For each order, fetch its details and the corresponding item names
        // This can be optimized, but for simplicity, we'll do it here.
        orders.forEach(order -> {
            List<OrderDetail> orderDetails = orderService.getOrderDetailsByOrderId(order.getOrderId());
            Map<Integer, Item> itemDetailsMap = orderDetails.stream()
                    .map(detail -> itemRepository.findById(detail.getItemId()))
                    .collect(Collectors.toMap(Item::getId, item -> item));
            model.addAttribute("orderDetails_" + order.getOrderId(), orderDetails);
            model.addAttribute("itemDetailsMap_" + order.getOrderId(), itemDetailsMap);
        });

        return "orders"; // Maps to /WEB-INF/jsp/orders.jsp
    }
}
