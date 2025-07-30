package com.example.SupplyStore.controller;

import com.example.SupplyStore.entity.Item;
import com.example.SupplyStore.repository.ItemRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String showDashboard(Model model, HttpSession session) {
        // Check if user is logged in
        if (session.getAttribute("customerId") == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }

        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "dashboard";
    }

    @GetMapping("/search")
    public String searchItems(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (session.getAttribute("customerId") == null) {
            return "redirect:/login";
        }
        List<Item> items = itemRepository.findByNameContainingIgnoreCase(keyword);
        model.addAttribute("items", items);
        return "dashboard";
    }
}
