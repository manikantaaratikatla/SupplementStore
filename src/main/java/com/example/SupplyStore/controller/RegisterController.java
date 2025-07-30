package com.example.SupplyStore.controller;

import com.example.SupplyStore.entity.Customer;
import com.example.SupplyStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public String showRegisterPage() {
        return "register"; // Loads register.jsp
    }

    @PostMapping("/save")
    public String registerUser(@RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address,
                               Model model) {
        // Check if email already exists
        if (customerRepository.findByEmail(email) != null) {
            model.addAttribute("error", "Email already registered. Try logging in.");
            return "register";
        }

        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setEmail(email);
        newCustomer.setPassword(password); // In a real app, hash this password!
        newCustomer.setPhone(phone);
        newCustomer.setAddress(address);

        try {
            customerRepository.save(newCustomer);
            model.addAttribute("message", "Registration successful. Please login.");
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Registration failed due to a database error.");
            return "register"; // Or an error page
        }
    }
}
