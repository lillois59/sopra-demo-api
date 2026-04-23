# Sopra Steria - PoC API Sécurisée (Secteur Défense)

Une API REST développée avec **Spring Boot 3** démontrant une architecture propre et des bonnes pratiques de sécurité (JWT + rôles).

**Lien de la démo en ligne :**  
[https://sopra-demo-api-production.up.railway.app](https://sopra-demo-api-production.up.railway.app)

**Swagger UI :**  
[https://sopra-demo-api-production.up.railway.app/swagger-ui.html](https://sopra-demo-api-production.up.railway.app/swagger-ui.html)

---

## Fonctionnalités implémentées

- Authentification JWT (stateless)
- Gestion des rôles : **ADMIN** et **USER**
- Protection des routes avec `@PreAuthorize`
- Hashage des mots de passe avec BCrypt
- Création d’utilisateurs réservée aux **ADMIN** (`/api/admin/users`)
- Liste des utilisateurs accessible aux rôles ADMIN et USER (`/api/users`)
- Documentation interactive avec Swagger
- Déploiement sur Railway
- Architecture en couches (Controller → Service → Repository)

---

## Technologies utilisées

- **Backend** : Spring Boot 3.5
- **Sécurité** : Spring Security + JWT
- **Base de données** : H2 (en mémoire)
- **Documentation** : Swagger OpenAPI
- **Build & Déploiement** : Maven + Railway

---

## Identifiants de test

**Admin par défaut :**
- **Username** : `sopra_admin`
- **Password** : `Admin2026!`

---

## Comment tester la démo

### 1. Via Swagger UI (recommandé)
1. Ouvrir [Swagger](https://sopra-demo-api-production.up.railway.app/swagger-ui.html)
2. Aller sur **POST /api/auth/login**
3. Utiliser les identifiants ci-dessus
4. Copier le token reçu
5. Cliquer sur le bouton **Authorize** en haut à droite
6. Coller `Bearer [token]` et valider
7. Tester ensuite :
   - **GET /api/users** (accessible aux ADMIN et USER)
   - **POST /api/admin/users** (réservé aux ADMIN)

### 2. Via Postman
- Utiliser les URLs avec `https://`
- Ajouter le header `Authorization: Bearer [token]` après le login

---

## Endpoints principaux

| Méthode | Endpoint                  | Description                          | Accès          |
|---------|---------------------------|--------------------------------------|----------------|
| POST    | `/api/auth/login`         | Authentification JWT                 | Public         |
| GET     | `/api/users`              | Liste des utilisateurs               | ADMIN + USER   |
| POST    | `/api/admin/users`        | Créer un utilisateur                 | ADMIN uniquement |

---

**Développé par** lilloi59  
**Pour** : Sopra Steria Lille - Secteur Défense  
**Date** : Avril 2026
