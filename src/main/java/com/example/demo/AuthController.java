package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Show the sign-in form
    @GetMapping("/signin")
    public String showSignInForm() {
        return "signin"; // This returns the signin.html from the templates directory
    }

    // Handle the sign-in form submission
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        String result = authService.authenticate(username, password);

        if (result.equals("200 Successful login")) {
            // Add the username to the model
            model.addAttribute("username", username);
            // Redirect to the dashboard
            return "dashboard"; // Return the dashboard view
        } else {
            // Add error message to the model
            model.addAttribute("error", result);
            return "signin"; // Return to the sign-in page with the error message
        }
    }

    // Handle registration
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        return authService.register(username, password);
    }

    // Optional: Logout endpoint
    @GetMapping("/logout")
    public String logout() {
        // Implement your logout logic here if needed
        return "redirect:/auth/signin";
    }
}

