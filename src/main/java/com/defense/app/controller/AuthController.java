package com.defense.app.controller;

import com.defense.app.dto.AuthResponse;
import com.defense.app.dto.LoginRequest;
import com.defense.app.model.User;
import com.defense.app.repository.UserRepository;
import com.defense.app.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername()).orElse(null);

        if (user == null) {
            System.out.println("❌ Utilisateur introuvable : " + req.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Identifiants invalides"));
        }

        boolean ok = passwordEncoder.matches(req.getPassword(), user.getPassword());
        System.out.println("🔐 Login " + user.getUsername() + " [" + user.getRole() + "] → " + (ok ? "✅ OK" : "❌ ECHEC"));

        if (!ok) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Identifiants invalides"));
        }

        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(user)));
    }
}