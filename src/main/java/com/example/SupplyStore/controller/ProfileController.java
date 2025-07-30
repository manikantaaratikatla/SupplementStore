package com.example.SupplyStore.controller;

import com.example.SupplyStore.entity.Customer;
import com.example.SupplyStore.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        // Fetch the latest customer data from the database
        Customer customer = customerRepository.findByCustomerId(customerId.longValue());
        if (customer == null) {
            // This should ideally not happen if customerId is valid
            session.invalidate(); // Invalidate session if customer not found
            return "redirect:/login";
        }
        model.addAttribute("customer", customer);
        return "profile"; // Maps to /WEB-INF/jsp/profile.jsp
    }
}
