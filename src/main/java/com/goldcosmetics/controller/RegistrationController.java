package com.goldcosmetics.controller;

import com.goldcosmetics.model.User;
import com.goldcosmetics.model.Role;
import com.goldcosmetics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller  // Marks this as a controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        
        // Create a new empty User object
        // The form will fill in its properties when submitted
        User user = new User();
        
        // Add the user to the model
        // In the HTML, we'll use th:object="${user}" to bind the form to this object
        model.addAttribute("user", user);
        
        // Add page title
        model.addAttribute("pageTitle", "Register - Gold Cosmetics");
        
        System.out.println("📝 Registration form requested");
        
        // Return the template name (Spring looks for register.html in templates/)
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                              BindingResult bindingResult,
                              Model model) {
        
        System.out.println("📨 Registration form submitted for: " + user.getUsername());
        
        // =======================================================================
        // STEP 1: Check for validation errors
        // =======================================================================
        // @Valid triggers validation based on annotations in User.java:
        // - @NotBlank checks if fields are filled
        // - @Email checks if email format is valid
        // - @Size checks if length is correct
        
        if (bindingResult.hasErrors()) {
            System.out.println("❌ Validation errors detected:");
            bindingResult.getAllErrors().forEach(error -> 
                System.out.println("   - " + error.getDefaultMessage())
            );
            
            model.addAttribute("pageTitle", "Register - Gold Cosmetics");
            return "register";  // Show form again with errors
        }
        
        // =======================================================================
        // STEP 2: Set default role to CUSTOMER
        // =======================================================================
        // Users registering themselves are always customers
        // Employees are created by admins
        user.setRole(Role.CUSTOMER);
        
        // =======================================================================
        // STEP 3: Try to register the user
        // =======================================================================
        try {
            // Call the service to register
            // This will:
            // 1. Check if username/email already exists
            // 2. Encrypt the password
            // 3. Save to database
            User savedUser = userService.registerUser(user);
            
            System.out.println("✅ Registration successful!");
            System.out.println("   User ID: " + savedUser.getId());
            System.out.println("   Username: " + savedUser.getUsername());
            System.out.println("   Email: " + savedUser.getEmail());
            System.out.println("   Role: " + savedUser.getRole());
            
            // Redirect to login page with success message
            // The "?success" query parameter will trigger a success message in login.html
            return "redirect:/login?success";
            
        } catch (RuntimeException e) {
            // =======================================================================
            // STEP 4: Handle registration errors
            
            System.out.println("❌ Registration failed: " + e.getMessage());
            
            // Add error message to model
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pageTitle", "Register - Gold Cosmetics");
            
            // Show the form again with the error message
            return "register";
        }
    }

    @GetMapping("/admin/register-employee")
    public String showEmployeeRegistrationForm(Model model) {
        User user = new User();
        user.setRole(Role.EMPLOYEE);  // Pre-set role to employee
        
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Register Employee - Gold Cosmetics");
        model.addAttribute("isEmployee", true);  // Flag for template
        
        return "register-employee";  // We'll create this template later
    }

    @PostMapping("/admin/register-employee")
    public String registerEmployee(@Valid @ModelAttribute("user") User user,
                                  BindingResult bindingResult,
                                  @RequestParam("shopLocation") String shopLocation,
                                  Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Register Employee - Gold Cosmetics");
            model.addAttribute("isEmployee", true);
            return "register-employee";
        }
        
        try {
            // Set employee-specific fields
            user.setRole(Role.EMPLOYEE);
            user.setShopLocation(shopLocation);
            
            userService.registerUser(user);
            
            System.out.println("✅ Employee registered: " + user.getUsername() 
                             + " at " + shopLocation);
            
            return "redirect:/admin/employees?success";
            
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pageTitle", "Register Employee - Gold Cosmetics");
            model.addAttribute("isEmployee", true);
            return "register-employee";
        }
    }
    
}