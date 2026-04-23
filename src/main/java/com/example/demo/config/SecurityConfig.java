package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
<<<<<<< HEAD
                .anyRequest().permitAll()   // Tout est accessible pour la démo
=======
                // Routes complètement publiques
                .requestMatchers("/", "/index.html", "/admin.html", "/api/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                // Routes protégées
                .requestMatchers("/api/users").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // Tout le reste doit être authentifié
                .anyRequest().authenticated()
>>>>>>> e25f57535c711214800d8ceb8a987ef00dd5f327
            );

        return http.build();
    }
}