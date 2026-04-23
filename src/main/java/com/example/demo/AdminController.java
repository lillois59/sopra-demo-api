package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Créer un utilisateur - Réservé aux ADMIN")
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user) {
        try {
            if (user == null || user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
                throw new IllegalArgumentException("Username, password and role are required");
            }

            // Log pour voir ce qui arrive
            System.out.println("Creating user: " + user.getUsername() + " with role " + user.getRole());

            return userService.createUser(user);

        } catch (Exception e) {
            System.err.println("Error in AdminController: " + e.getMessage());
            e.printStackTrace();
            throw e;   // Pour voir l'erreur réelle
        }
    }
}