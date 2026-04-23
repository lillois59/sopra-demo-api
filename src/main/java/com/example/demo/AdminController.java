package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
            user.setId(null);

            if (user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
                return ResponseEntity.badRequest().body("Username, password and role are required");
            }

            User created = userService.createUser(user);
            return ResponseEntity.ok(created);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }
}