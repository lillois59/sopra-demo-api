package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}