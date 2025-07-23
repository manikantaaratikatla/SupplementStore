package com.example.SupplyStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

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

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/supplementsstore", "root", "M@n!1323");

            // Check if email already exists
            String checkQuery = "SELECT * FROM customer WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                model.addAttribute("error", "Email already registered. Try logging in.");
                return "register";
            }

            // Insert new user with full details
            String insertQuery = "INSERT INTO customer (name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, name);
            insertStmt.setString(2, email);
            insertStmt.setString(3, password);
            insertStmt.setString(4, phone);
            insertStmt.setString(5, address);

            int rowsInserted = insertStmt.executeUpdate();

            if (rowsInserted > 0) {
                model.addAttribute("message", "Registration successful. Please login.");
                return "login"; // login.jsp should exist in the same JSP folder
            } else {
                model.addAttribute("error", "Registration failed. Please try again.");
                return "register";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error occurred.");
            return "error";
        }
    }
}
