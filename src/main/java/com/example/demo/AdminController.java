package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            if (user == null || user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
                return ResponseEntity.badRequest().build();
            }

            User created = userService.createUser(user);
            return ResponseEntity.ok(created);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}