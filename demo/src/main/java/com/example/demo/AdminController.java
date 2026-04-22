package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Créer un utilisateur - Réservé aux ADMINISTRATEURS")
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
            throw new IllegalArgumentException("Username, password and role are required");
        }
        return userService.createUser(user);
    }
}