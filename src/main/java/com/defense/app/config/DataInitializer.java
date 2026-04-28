package com.defense.app.config;

import com.defense.app.model.User;
import com.defense.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        try {
            if (userRepository.findByUsername("sopra_admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("sopra_admin");
                admin.setPassword(passwordEncoder.encode("Admin2026!"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("================================================");
                System.out.println("✅  ADMIN créé  →  sopra_admin / Admin2026!");
                System.out.println("================================================");
            } else {
                System.out.println("✅  Admin déjà en base : sopra_admin");
            }

            if (userRepository.findByUsername("sopra_user").isEmpty()) {
                User user = new User();
                user.setUsername("sopra_user");
                user.setPassword(passwordEncoder.encode("User2026!"));
                user.setRole("USER");
                userRepository.save(user);
                System.out.println("✅  USER  créé  →  sopra_user / User2026!");
            } else {
                System.out.println("✅  User déjà en base : sopra_user");
            }
        } catch (Exception e) {
            System.out.println("⚠️  DataInitializer erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}