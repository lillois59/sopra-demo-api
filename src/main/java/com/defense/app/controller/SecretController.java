package com.defense.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/secret")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecretController {

    @GetMapping("/documents")
    public ResponseEntity<List<Map<String, String>>> getDocuments() {
        List<Map<String, String>> docs = List.of(
            Map.of("classification", "SECRET DÉFENSE", "code", "OP-MISTRAL-7",
                   "titre", "Opération Mistral — Phase 7",
                   "contenu", "Déploiement de la flotte en secteur Nord-Est. Coordonnées cryptées. Accès restreint niveau 4."),
            Map.of("classification", "CONFIDENTIEL", "code", "CRYPTO-443",
                   "titre", "Protocole de chiffrement CRYPTO-443",
                   "contenu", "Mise à jour des clés AES-256 pour les communications terrain. Rotation prévue J+30."),
            Map.of("classification", "SECRET DÉFENSE", "code", "DRONE-RECON-9",
                   "titre", "Mission de reconnaissance DRONE-RECON-9",
                   "contenu", "Surveillance périmètre Bravo. Données satellites annexées. Validité 48h."),
            Map.of("classification", "TRÈS SECRET", "code", "PROJET-ARES",
                   "titre", "Projet ARÈS — Dossier initial",
                   "contenu", "Développement système d'interception autonome nouvelle génération. Financement validé. Équipe : 12 ingénieurs habilités.")
        );
        return ResponseEntity.ok(docs);
    }
}