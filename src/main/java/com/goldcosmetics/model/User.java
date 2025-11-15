package com.goldcosmetics.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

// LOMBOK ANNOTATIONS (Generates code for us!)
@Data                    // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor       // Generates a no-argument constructor
@AllArgsConstructor      // Generates a constructor with all fields
@Builder                 // Generates a builder pattern (User.builder().name("Angela").build())

// JPA ANNOTATIONS (Maps this class to a database table)
@Entity                  // Tells JPA: "This is a database entity"
@Table(name = "users")   // Table name in PostgreSQL will be "users"
public class User {

    @Id  // Marks this as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    private Long id;
    
    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    private String username;
    
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Name must be 2-100 characters")
    private String fullName;
    
    @Column(length = 20)
    @Pattern(regexp = "^[0-9+\\-\\s()]*$", message = "Please provide a valid phone number")
    private String phone;
    
    @Enumerated(EnumType.STRING)  // Store as "CUSTOMER" not 0, 1, 2
    @Column(nullable = false)
    private Role role = Role.CUSTOMER;  // Default role
    
    @Column(length = 50)
    private String shopLocation;
    
    @Column(length = 255)
    private String deliveryAddress;
    
    @Column(nullable = false)
    private Boolean isActive = true;  // Active by default
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    
    private LocalDateTime lastLoginAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    /**
     * Check if this user is a customer
     */
    public boolean isCustomer() {
        return this.role == Role.CUSTOMER;
    }
    
    /**
     * Check if this user is an employee
     */
    public boolean isEmployee() {
        return this.role == Role.EMPLOYEE;
    }
    
    /**
     * Check if this user is an admin
     */
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
    
}