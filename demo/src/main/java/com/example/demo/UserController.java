package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Gestion des utilisateurs")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Créer un nouvel utilisateur")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Récupérer la liste de tous les utilisateurs")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}