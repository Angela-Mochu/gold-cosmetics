package com.goldcosmetics.controller;

// =======================================================================
// IMPORTS
// =======================================================================
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// =======================================================================
// HOME CONTROLLER
// =======================================================================
/**
 * HomeController - Handles the Home Page of Gold Cosmetics
 * 
 * WHAT IS A CONTROLLER?
 * Think of a controller as a "traffic cop" for your website. When someone
 * visits a URL (like http://localhost:8080), the controller decides:
 * 1. What data to fetch from the database
 * 2. What HTML page to show the user
 * 3. What information to pass to that page
 * 
 * SIMPLE ANALOGY:
 * You walk into a restaurant (visit a URL) and tell the waiter (controller)
 * "I want the menu" (make a request). The waiter goes to the kitchen (service),
 * gets the menu (data), and brings it to your table (returns HTML page).
 * 
 * @author Angela
 * @version 1.0
 */

// @Controller tells Spring: "This class handles web requests"
@Controller
public class HomeController {
    
    /**
     * HOME PAGE METHOD
     * 
     * This method handles requests to the home page (/).
     * When someone visits http://localhost:8080, this code runs!
     * 
     * @GetMapping("/") means: "When someone does a GET request to '/', run this method"
     * 
     * GET requests are used when you want to VIEW a page (not send data).
     * POST requests are used when you want to SEND data (like submitting a form).
     * 
     * @param model - A container for data we want to send to the HTML page
     * @return The name of the HTML file to show (without .html extension)
     */
    @GetMapping("/")
    public String home(Model model) {
        
        // ===================================================================
        // ADDING DATA TO THE PAGE
        // ===================================================================
        // model.addAttribute() lets us send data to the HTML page
        // Format: model.addAttribute("variableName", "value")
        
        // Send the store name
        model.addAttribute("storeName", "Gold Cosmetics");
        
        // Send a welcome message
        model.addAttribute("welcomeMessage", 
            "Welcome to Gold Cosmetics - Your Beauty Destination!");
        
        // Send the two shop locations
        model.addAttribute("shop1", "Naivasha Town");
        model.addAttribute("shop2", "Karagita");
        
        // Send current year for copyright
        model.addAttribute("currentYear", java.time.Year.now().getValue());
        
        // ===================================================================
        // RETURN THE HTML TEMPLATE
        // ===================================================================
        // "home" means Spring will look for a file called "home.html" 
        // in the templates folder
        return "home";
        
        // WHAT HAPPENS NEXT?
        // 1. Spring finds templates/home.html
        // 2. It replaces placeholders with the data we added above
        // 3. It sends the final HTML to the user's browser
        // 4. The user sees a beautiful web page! 🎉
    }
    
    /**
     * ABOUT PAGE METHOD
     * 
     * Let's create another page! This handles http://localhost:8080/about
     */
    @GetMapping("/about")
    public String about(Model model) {
        
        model.addAttribute("storeName", "Gold Cosmetics");
        model.addAttribute("aboutText", 
            "We are Gold Cosmetics, operating two stores in Naivasha and Karagita. " +
            "Our mission is to provide quality beauty products to our customers " +
            "with excellent service and competitive prices.");
        
        return "about";
    }
    
    // =======================================================================
    // NOTES FOR ANGELA:
    // =======================================================================
    // 1. Controllers are the "glue" between your database and web pages
    // 2. @GetMapping creates a URL route (like a street address for your page)
    // 3. The Model object carries data from Java to HTML
    // 4. The return value must match an HTML file name in templates/
    // 5. You'll create MANY controllers as we add features (products, cart, checkout, etc.)
    //
    // NEXT STEPS:
    // - We'll create the home.html file to display this data
    // - Later, we'll add more controllers for products, users, orders, etc.
    // =======================================================================
}
