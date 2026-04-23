package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if (user == null || user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
                return ResponseEntity.badRequest().body("Username, password and role are required");
            }

            // Log pour debug
            System.out.println("=== ADMIN CREATE USER ===");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());

            User created = userService.createUser(user);

            System.out.println("User created successfully: " + created.getUsername());
            return ResponseEntity.ok(created);

        } catch (DataIntegrityViolationException e) {
            System.err.println("Duplicate username: " + user.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username '" + user.getUsername() + "' already exists");
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal error: " + e.getMessage());
        }
    }
}