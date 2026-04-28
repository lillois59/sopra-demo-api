package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";   // Redirige vers ta page d'accueil
    }

    @GetMapping("/home")
    public String homePage() {
        return "forward:/index.html";
    }
}