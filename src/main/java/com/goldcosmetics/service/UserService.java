package com.goldcosmetics.service;

import com.goldcosmetics.model.User;
import com.goldcosmetics.model.Role;
import com.goldcosmetics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service  // Marks this as a service component
@Transactional  // Wraps methods in database transactions (rollback if error)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        
        // STEP 1: Check if username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already taken. Please choose another.");
        }
        
        // STEP 2: Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered. Please use a different email.");
        }
        
        // STEP 3: Encrypt the password
        // NEVER store plain passwords! Always encrypt!
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        
        // STEP 4: Set default values if not provided
        if (user.getRole() == null) {
            user.setRole(Role.CUSTOMER);  // Default to customer
        }
        
        if (user.getIsActive() == null) {
            user.setIsActive(true);  
        }
        
        // STEP 5: Save to database and return
        User savedUser = userRepository.save(user);
        
        System.out.println("✅ New user registered: " + savedUser.getUsername() 
                         + " (ID: " + savedUser.getId() + ")");
        
        return savedUser;
    }
    
    /**
     * Register a customer (simplified method)
     * 
     * This is a convenience method for customer registration
     */
    public User registerCustomer(String username, String email, String password, 
                                 String fullName, String phone, String deliveryAddress) {
        
        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)  // Will be encrypted in registerUser()
                .fullName(fullName)
                .phone(phone)
                .deliveryAddress(deliveryAddress)
                .role(Role.CUSTOMER)
                .isActive(true)
                .build();
        
        return registerUser(user);
    }
    
    /**
     * Register an employee (simplified method)
     * 
     * This is used by admins to create employee accounts
     */
    public User registerEmployee(String username, String email, String password,
                                 String fullName, String phone, String shopLocation) {
        
        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)  // Will be encrypted in registerUser()
                .fullName(fullName)
                .phone(phone)
                .shopLocation(shopLocation)
                .role(Role.EMPLOYEE)
                .isActive(true)
                .build();
        
        return registerUser(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    /**
     * Find user by email
     * 
     * Used for email-based login and "forgot password" features
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * Find user by ID
     * 
     * Used throughout the app to get user details
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    /**
     * Get all users
     * 
     * Used by admin to view all registered users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Get all customers
     */
    public List<User> getAllCustomers() {
        return userRepository.findByRole(Role.CUSTOMER);
    }
    
    /**
     * Get all employees
     */
    public List<User> getAllEmployees() {
        return userRepository.findByRole(Role.EMPLOYEE);
    }

    public List<User> getEmployeesByShop(String shopLocation) {
        return userRepository.findByRoleAndShopLocation(Role.EMPLOYEE, shopLocation);
    }

    public User updateUser(Long userId, User updatedUser) {
        
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        
        // Update allowed fields
        if (updatedUser.getFullName() != null) {
            existingUser.setFullName(updatedUser.getFullName());
        }
        if (updatedUser.getPhone() != null) {
            existingUser.setPhone(updatedUser.getPhone());
        }
        if (updatedUser.getDeliveryAddress() != null) {
            existingUser.setDeliveryAddress(updatedUser.getDeliveryAddress());
        }
        
        // Save and return
        return userRepository.save(existingUser);
    }

    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Verify old password
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;  // Old password is incorrect
        }
        
        // Encrypt and save new password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        System.out.println("✅ Password changed for user: " + user.getUsername());
        return true;
    }

    public void updateLastLogin(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setLastLoginAt(LocalDateTime.now());
            userRepository.save(user);
        }
    }
    
    public void activateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(true);  
        userRepository.save(user);
        System.out.println("✅ User activated: " + user.getUsername());
    }

    public void deactivateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(true);  
        userRepository.save(user);
        System.out.println("⛔ User deactivated: " + user.getUsername());
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        System.out.println("🗑️ User deleted: ID " + userId);
    }

    public void changeUserRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(newRole);
        userRepository.save(user);
        System.out.println("✅ Role changed for " + user.getUsername() + " to " + newRole);
    }
   
    /**
     * Count total users
     */
    public long getTotalUsers() {
        return userRepository.count();
    }
    
    /**
     * Count customers
     */
    public long getTotalCustomers() {
        return userRepository.countByRole(Role.CUSTOMER);
    }
    
    /**
     * Count employees
     */
    public long getTotalEmployees() {
        return userRepository.countByRole(Role.EMPLOYEE);
    }
    
    /**
     * Count admins
     */
    public long getTotalAdmins() {
        return userRepository.countByRole(Role.ADMIN);
    }

}