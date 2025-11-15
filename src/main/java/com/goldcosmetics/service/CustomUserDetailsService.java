package com.goldcosmetics.service;

import com.goldcosmetics.model.User;
import com.goldcosmetics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

@Service  // Marks this as a service component
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        System.out.println("🔍 Spring Security looking for user: " + username);
        
        // STEP 1: Try to find user in database
        User user = userRepository.findByUsername(username)
                .or(() -> userRepository.findByEmail(username))  // Try email if username fails
                .orElseThrow(() -> {
                    System.out.println("❌ User not found: " + username);
                    return new UsernameNotFoundException("User not found: " + username);
                });
        
        System.out.println("✅ User found: " + user.getUsername() + " (Role: " + user.getRole() + ")");
        
        // STEP 2: Check if account is active
        if (!user.getIsActive()) {
            System.out.println("⛔ Account is deactivated: " + username);
            throw new UsernameNotFoundException("Account is deactivated");
        }
        

        return buildUserDetails(user);
    }
    
    private UserDetails buildUserDetails(User user) {
        
        // Build the user details
        return org.springframework.security.core.userdetails.User.builder()
                // Username (can be username or email)
                .username(user.getUsername())
                
                // Password (already encrypted in database)
                .password(user.getPassword())
                
                // Authorities (roles)
                // We convert Role.CUSTOMER → "ROLE_CUSTOMER"
                // Spring Security requires the "ROLE_" prefix
                .authorities(getAuthorities(user))
                
                // Account status
                .accountExpired(false)       // Our accounts don't expire
                .accountLocked(!user.getIsActive())  // Locked if not active
                .credentialsExpired(false)   // Passwords don't expire
                .disabled(!user.getIsActive())  // Disabled if not active
                
                .build();
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        
        // Create authority from role
        // Format: "ROLE_" + role name
        String authority = "ROLE_" + user.getRole().name();
        
        System.out.println("👤 User " + user.getUsername() + " has authority: " + authority);
        
        // Return as a collection (Spring Security expects a collection)
        return Collections.singletonList(new SimpleGrantedAuthority(authority));
    }
    

}