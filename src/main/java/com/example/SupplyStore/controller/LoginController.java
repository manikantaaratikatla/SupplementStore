package com.example.SupplyStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/auth")
    public String authenticateUser(@RequestParam("username") String userName,
                                   @RequestParam("password") String password,
                                   Model model) {
        try {
            String query = "SELECT * FROM customer WHERE email = ? AND password = ?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/supplementsstore", "root", "M@n!1323");

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                model.addAttribute("name", rs.getString("name"));
               
                return "redirect:/dashboard";
            } else {
                model.addAttribute("error", "Invalid username or password.");
                return "login";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error occurred.");
            return "error";
        }
    }
}