package com.example.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DemoController {

    @GetMapping("/hello")
    public String hello(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("user", user);
        return "hello";
    }

    @GetMapping("/customers")
    public String customers() {
        // Requires authentication (configured in SecurityConfig)
        return "customers";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/hello";
    }
}
