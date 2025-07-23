package com.example.SupplyStore.controller;

import com.example.SupplyStore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ItemRepository itemRepository;

    @Autowired
    public DashboardController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "dashboard"; // resolves to dashboard.jsp
    }
}