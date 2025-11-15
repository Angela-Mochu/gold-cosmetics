package com.goldcosmetics;

// =======================================================================
// IMPORTS: Bringing in code from other libraries
// =======================================================================
// Think of imports like saying "I need to use a hammer" before building.
// We're telling Java which tools (classes) we need.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// =======================================================================
// MAIN APPLICATION CLASS
// =======================================================================
/**
 * GoldCosmeticsApplication - The Starting Point of Our Entire System
 * 
 * WHAT IS THIS FILE?
 * This is like the "main switch" that turns on your entire application.
 * When you run this file, it starts the web server, connects to the database,
 * and makes everything work together.
 * 
 * SIMPLE ANALOGY:
 * Think of this as the ignition key for a car. Turn it on, and the whole
 * car starts running. This file does the same for your web application.
 * 
 * @author Angela
 * @version 1.0
 * @since 2025-11-14
 */

// This annotation tells Spring Boot: "Hey, this is a Spring Boot application!"
// It automatically configures a lot of things for us (saves us hours of setup!)
@SpringBootApplication
public class GoldCosmeticsApplication {

    /**
     * THE MAIN METHOD - Where Everything Begins
     * 
     * In Java, every application needs a "main" method. This is where
     * the program starts running when you hit "Run".
     * 
     * @param args - Command line arguments (we won't use these for now)
     */
    public static void main(String[] args) {
        
        // Print a welcome message to the console (you'll see this when you run the app)
        System.out.println("========================================");
        System.out.println("🌟 STARTING GOLD COSMETICS SYSTEM 🌟");
        System.out.println("========================================");
        System.out.println("💄 Naivasha Shop: Online");
        System.out.println("💅 Karagita Shop: Online");
        System.out.println("========================================");
        System.out.println("📍 Access the application at: http://localhost:8080");
        System.out.println("========================================");
        
        // This line actually STARTS the Spring Boot application
        // SpringApplication.run() does ALL the magic:
        //   - Starts the web server (Tomcat)
        //   - Connects to the database
        //   - Loads all your controllers, services, and repositories
        //   - Makes your website accessible at http://localhost:8080
        SpringApplication.run(GoldCosmeticsApplication.class, args);
        
        // After this line runs, your application is LIVE! 🚀
    }
    
    // =======================================================================
    // NOTES FOR ANGELA:
    // =======================================================================
    // 1. This file rarely changes once created - it's mostly setup
    // 2. The @SpringBootApplication annotation does a LOT of work behind the scenes
    // 3. You'll spend most of your time in Controllers, Services, and Models
    // 4. If you see this running in the console without errors = SUCCESS! 🎉
    // =======================================================================
}
