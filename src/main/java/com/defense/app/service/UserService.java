package com.defense.app.service;

import com.defense.app.model.User;
import com.defense.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        if (user.getPassword() == null || user.getPassword().isBlank())
            throw new IllegalArgumentException("Mot de passe obligatoire");
        // Protection double-encodage
        if (!user.getPassword().startsWith("$2a$") && !user.getPassword().startsWith("$2b$"))
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public List<User> getAllUsers() { return repo.findAll(); }

    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public User getByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + username));
    }
}