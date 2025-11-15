package com.goldcosmetics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration  // Marks this as a configuration class
@EnableWebSecurity  // Enables Spring Security
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
            .authorizeHttpRequests(auth -> auth
                
                .requestMatchers(
                    "/",              // Homepage
                    "/about",         // About page
                    "/register",      // Registration page
                    "/login",         // Login page
                    "/css/**",        // CSS files
                    "/js/**",         // JavaScript files
                    "/images/**",     // Images
                    "/error"          // Error pages
                ).permitAll()
                
                // ADMIN-ONLY PAGES
                // Only users with ADMIN role can access these
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // EMPLOYEE-ONLY PAGES (Coming later!)
                // Only users with EMPLOYEE or ADMIN role can access
                .requestMatchers("/employee/**").hasAnyRole("EMPLOYEE", "ADMIN")
                
                // CUSTOMER PAGES (Coming later!)
                // Only authenticated customers can access
                .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "EMPLOYEE", "ADMIN")
                
                // ALL OTHER PAGES
                // Require authentication (logged in)
                .anyRequest().authenticated()
            )
            
            .formLogin(form -> form
                .loginPage("/login")           // Our custom login page
                .loginProcessingUrl("/login")  // URL that processes login form
                .defaultSuccessUrl("/dashboard", true)  // Where to go after successful login
                .failureUrl("/login?error")    // Where to go if login fails
                .permitAll()                   // Everyone can access login page
            )
            
            .logout(logout -> logout
                .logoutUrl("/logout")          // URL to trigger logout
                .logoutSuccessUrl("/login?logout")  // Where to go after logout
                .invalidateHttpSession(true)   // Clear the session
                .deleteCookies("JSESSIONID")   // Delete session cookie
                .permitAll()                   // Everyone can logout
            )
            
            .rememberMe(remember -> remember
                .key("goldCosmeticsSecretKey")  // Secret key for remember-me token
                .tokenValiditySeconds(86400 * 7)  // Valid for 7 days
            )
            
            .sessionManagement(session -> session
                .maximumSessions(1)            // Only one session per user
                .expiredUrl("/login?expired")  // Where to go if session expires
            );
        
        return http.build();
    }
}