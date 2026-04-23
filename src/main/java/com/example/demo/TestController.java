package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {

    @GetMapping
    public String getTest() {
        return "GET /api/test OK - " + java.time.LocalDateTime.now();
    }

    @PostMapping
    public String postTest(@RequestBody String body) {
        return "POST /api/test OK - Received: " + body;
    }

    @PostMapping("/login")
    public String testLogin() {
        return "POST /api/test/login OK - Login endpoint simulation";
    }
}