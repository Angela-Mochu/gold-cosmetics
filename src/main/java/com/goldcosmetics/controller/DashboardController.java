package com.goldcosmetics.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collection;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        
        // Get the user's username
        String username = authentication.getName();
        
        // Get the user's authorities (roles)
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        // Add user info to model
        model.addAttribute("username", username);
        
        // Check user's role and add appropriate info
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();  // e.g., "ROLE_CUSTOMER"
            
            if (role.equals("ROLE_ADMIN")) {
                model.addAttribute("role", "Admin");
                model.addAttribute("roleIcon", "👑");
                model.addAttribute("welcomeMessage", "Welcome to the Admin Dashboard!");
                // Later: redirect to /admin/dashboard
                
            } else if (role.equals("ROLE_EMPLOYEE")) {
                model.addAttribute("role", "Employee");
                model.addAttribute("roleIcon", "👔");
                model.addAttribute("welcomeMessage", "Welcome to the Employee Dashboard!");
                // Later: redirect to /employee/dashboard
                
            } else if (role.equals("ROLE_CUSTOMER")) {
                model.addAttribute("role", "Customer");
                model.addAttribute("roleIcon", "🛍️");
                model.addAttribute("welcomeMessage", "Welcome back! Start shopping!");
                // Later: redirect to /customer/dashboard
            }
        }
        
        System.out.println("✅ User " + username + " accessed dashboard");
        
        return "dashboard";
    }
}