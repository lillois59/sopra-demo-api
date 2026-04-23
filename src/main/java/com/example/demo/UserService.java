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
        // Hashage du mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Sauvegarde
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getByUsername(String username) {
        return repo.findByUsername(username);
    }
}