package com.example.SupplyStore.controller;

import com.example.SupplyStore.entity.Customer;
import com.example.SupplyStore.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public String showLoginPage() {
        return "login"; // Maps to /WEB-INF/jsp/login.jsp
    }

    @PostMapping("/auth")
    public String authenticateUser(@RequestParam("username") String email,
                                   @RequestParam("password") String password,
                                   HttpSession session,
                                   Model model) {
        Customer customer = customerRepository.findByEmail(email);

        if (customer != null && customer.getPassword().equals(password)) {
            session.setAttribute("customerId", customer.getCustomerId().intValue()); // Store customer ID in session
            session.setAttribute("loggedInCustomer", customer); // Store full customer object
            model.addAttribute("name", customer.getName());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }
}
