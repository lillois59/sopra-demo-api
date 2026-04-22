# PoC API - Gestion des Utilisateurs Sécurisée

**Projet Technique - Sopra Steria Secteur Défense**

Une API REST sécurisée développée avec Spring Boot, démontrant une architecture propre et des bonnes pratiques de sécurité (authentification JWT + contrôle d'accès par rôles).

## Technologies utilisées

- **Backend** : Spring Boot 3.5
- **Sécurité** : Spring Security + JWT (Json Web Token)
- **Base de données** : H2 (en mémoire)
- **Documentation** : Swagger UI
- **Authentification** : JWT avec rôles (ADMIN / USER)
- **Hashage des mots de passe** : BCrypt

## Fonctionnalités implémentées

- Authentification sécurisée par JWT (stateless)
- Gestion des rôles (ADMIN / USER)
- Création d'utilisateurs protégée (uniquement par les ADMIN)
- Liste des utilisateurs accessible aux rôles ADMIN et USER
- Documentation interactive avec Swagger
- Architecture en couches (Controller → Service → Repository)

## Comment tester la démo

### 1. Page d'accueil
Accédez à :  
`https://sopra-demo-api-production.up.railway.app/`

### 2. Documentation interactive (Swagger UI)
Accédez à :  
`https://sopra-demo-api-production.up.railway.app/swagger-ui/index.html`

### 3. Étapes de test

 **Se connecter avec l'admin par défaut**

POST /api/auth/login
{
  "username": "sopra_admin",
  "password": "Admin2026!"
}

b. Récupérer la liste des utilisateurs 
Copiez le token JWT reçu dans la réponse
Allez sur GET /api/users
Cliquez sur le bouton Authorize (en haut à droite de Swagger)
Collez le token sous la forme : Bearer votre_token_ici
Cliquez sur Execute

c. Créer un nouvel utilisateur (réservé aux ADMIN) 
Utilisez l’endpoint POST /api/admin/users
Utilisez le token d’un administrateur dans le bouton Authorize
Body :

json

{
  "username": "nouveau_user",
  "password": "password123",
  "role": "USER"
}

Structure du projetconfig/ → Configuration Spring Security

security/ → Gestion JWT + Filtre d'authentification

dto/ → Objets de transfert (LoginRequest, AuthResponse)

controller/ → Contrôleurs (UserController, AdminController)

service/ → Logique métier

repository/ → Accès à la base de données

Développé par

Lillois59

full-stack web dev

