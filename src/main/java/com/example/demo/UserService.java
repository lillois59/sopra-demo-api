package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        try {
            System.out.println("=== USER SERVICE CREATE ===");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());

            // Hashage du mot de passe
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            System.out.println("Password hashed successfully");

            User saved = repo.save(user);
            System.out.println("User saved successfully with id: " + saved.getId());

            return saved;

        } catch (Exception e) {
            System.err.println("Error in createUser: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getByUsername(String username) {
        return repo.findByUsername(username);
    }
}