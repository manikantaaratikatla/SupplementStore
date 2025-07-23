package com.example.SupplyStore.controller;

import com.example.SupplyStore.entity.CartItem;
import com.example.SupplyStore.repository.ItemRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/add")
    public String addToCart(@RequestParam("id") Long id,
                            @RequestParam("name") String name,
                            @RequestParam("price") double price,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;
        for (CartItem item : cart) {
            if (item.getId().equals(id)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(id, name, price, quantity));
        }

        session.setAttribute("cart", cart);
        return "redirect:/dashboard";
    }

    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("id") Long id, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(item -> item.getId().equals(id));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart/view";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        session.setAttribute("cart", new ArrayList<CartItem>());
        return "redirect:/cart/view";
    }
    @PostMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "Cart is empty. Add items before checkout.");
            return "cart";
        }

        session.removeAttribute("cart"); // Clear cart
        model.addAttribute("message", "Order placed successfully!");
        return "cart";
    }

   
}
