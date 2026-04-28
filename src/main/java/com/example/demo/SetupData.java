package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SetupData implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Création de l'admin par défaut si il n'existe pas
        if (userService.getByUsername("sopra_admin").isEmpty()) {
            User admin = new User("sopra_admin", "Admin2026!", "ADMIN");
            userService.createUser(admin);
            System.out.println("Admin par défaut créé : sopra_admin");
        }
    }
}