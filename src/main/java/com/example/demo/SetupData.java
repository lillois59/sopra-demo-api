package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SetupData implements CommandLineRunner {

    private final UserService userService;

    public SetupData(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Vérifie si l'admin existe déjà
            if (userService.findByUsername("sopra_admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("sopra_admin");
                admin.setPassword("Admin2026!");
                admin.setRole("ADMIN");

                userService.createUser(admin);
                System.out.println("✅ Admin par défaut créé avec succès : username = sopra_admin | password = Admin2026!");
            } else {
                System.out.println("✅ Admin déjà existant : sopra_admin");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Erreur lors de la création de l'admin par défaut : " + e.getMessage());
        }
    }
}