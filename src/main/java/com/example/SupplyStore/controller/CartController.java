package com.example.SupplyStore.controller;

import com.example.SupplyStore.entity.CartItem;
import com.example.SupplyStore.entity.Item;
import com.example.SupplyStore.repository.ItemRepository;
import com.example.SupplyStore.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ItemRepository itemRepository; // To get item details for display

    @PostMapping("/add")
    public String addToCart(@RequestParam("itemId") int itemId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }
        cartService.addToCart(customerId, itemId, quantity);
        return "redirect:/dashboard"; // Redirect back to dashboard after adding
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        List<CartItem> cartItems = cartService.getCartItemsByCustomerId(customerId);
        model.addAttribute("cartItems", cartItems);

        // Fetch item details for each cart item to display name, price, etc.
        Map<Integer, Item> itemDetailsMap = cartItems.stream()
                .map(cartItem -> itemRepository.findById(cartItem.getItemId()))
                .collect(Collectors.toMap(Item::getId, item -> item));
        model.addAttribute("itemDetailsMap", itemDetailsMap);

        double totalAmount = cartService.calculateCartTotal(cartItems);
        model.addAttribute("totalAmount", totalAmount);

        return "cart"; // Maps to /WEB-INF/jsp/cart.jsp
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("cartItemId") int cartItemId, HttpSession session) {
        if (session.getAttribute("customerId") == null) {
            return "redirect:/login";
        }
        cartService.removeItemFromCart(cartItemId);
        return "redirect:/cart"; // Redirect back to cart page
    }
}
