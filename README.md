# SRM-FM — Système Intelligent de Gestion des Interventions

## Présentation

SRM-FM est une application web intelligente de gestion des réclamations et des interventions techniques développée dans le cadre d’un Projet de Fin d’Année (PFA).

Le système permet de gérer l’ensemble du cycle de traitement d’une réclamation :

* création d’une réclamation,
* qualification par spécialité,
* création d’intervention,
* affectation des techniciens,
* suivi des statuts,
* horodatage des actions,
* analyse des performances,
* chatbot conversationnel.

L’objectif principal est d’optimiser la gestion des interventions techniques dans un environnement similaire aux sociétés de distribution d’eau, d’électricité ou de maintenance.

---

# Fonctionnalités Principales

## Authentification & Sécurité

* Authentification JWT sécurisée
* Gestion des rôles et permissions
* Protection des routes Angular
* Contrôle d’accès par spécialité
* Spring Security

## Gestion des Réclamations

* Création de réclamations publiques
* Qualification des réclamations
* Gestion des urgences
* Suivi des statuts

## Gestion des Interventions

* Création des interventions
* Affectation des techniciens
* Gestion des équipes
* Horodatage automatique des actions
* Clôture et rapports d’intervention

## Dashboards & KPIs

* Tableaux de bord par rôle
* Statistiques des interventions
* Graphiques Chart.js
* KPIs des techniciens

## Chatbot Conversationnel

* Assistant conversationnel intégré
* Machine à états TypeScript
* Création guidée des réclamations

## DevOps

* Docker & Docker Compose
* CI/CD avec GitHub Actions
* Déploiement conteneurisé

---

# Architecture Technique

## Frontend

* Angular 17
* TypeScript
* RxJS
* Chart.js
* Standalone Components

## Backend

* Spring Boot 3
* Java 17
* Spring Security
* Spring Data JPA
* JWT Authentication

## Base de données

* PostgreSQL

## DevOps

* Docker
* Docker Compose
* GitHub Actions
* GHCR

---

# Architecture Générale

```text
Frontend Angular
       ↓
REST API Spring Boot
       ↓
Spring Security + JWT
       ↓
PostgreSQL Database
       ↓
Docker / CI-CD
```

---

# Structure du Projet

```text
gestion_interventions/
│
├── backend/
│   ├── src/
│   ├── pom.xml
│
├── frontend/
│   ├── src/
│   ├── package.json
│
├── docker-compose.yml
├── README.md
```

---

# Rôles Utilisateurs

| Rôle           | Fonction                                          |
| -------------- | ------------------------------------------------- |
| Administrateur | Gestion des utilisateurs, équipes et référentiels |
| Ordonnanceur   | Qualification des réclamations                    |
| Chef d’équipe  | Affectation des techniciens                       |
| Technicien     | Gestion et suivi des interventions                |
| Client         | Création et suivi des réclamations                |

---

# Sécurité

Le système repose sur une architecture sécurisée basée sur JWT et Spring Security.

Fonctionnalités de sécurité :

* Authentification stateless
* Vérification des rôles
* Protection des endpoints REST
* Contrôle d’accès par spécialité
* Intercepteurs JWT Angular
* Guards Angular

---

# Installation du Projet

## Prérequis

* Java 17
* Node.js
* Angular CLI
* PostgreSQL
* Docker

---

# Backend

cd backend
mvn spring-boot:run
```

---

# Frontend

cd frontend
npm install
ng serve
```

Application Angular :

http://localhost:4200


# Docker

Lancement complet du système :

docker-compose up -d


# CI/CD

Le projet utilise GitHub Actions pour :

* build automatique,
* tests,
* conteneurisation Docker,
* publication des images.


# Perspectives d’Amélioration

* Intégration d’un véritable modèle d’intelligence artificielle
* Notifications temps réel
* Application mobile
* Déploiement cloud scalable
* Monitoring avancé
* Géolocalisation des interventions
* Intégration WebSockets


# Captures d’Écran

## Dashboard Administrateur
<img width="916" height="745" alt="image" src="https://github.com/user-attachments/assets/2321c36b-1c63-4bf6-a1ea-27ab8ac13b1d" />


## Dashboard Technicien
<img width="916" height="530" alt="image" src="https://github.com/user-attachments/assets/912e5fc2-cb6e-42dc-b2d8-7643d25ee935" />


## Gestion des Réclamations

<img width="906" height="420" alt="image" src="https://github.com/user-attachments/assets/bda56315-0080-46ba-b155-5383dea709cf" />


## Chatbot

<img width="916" height="451" alt="image" src="https://github.com/user-attachments/assets/ba69458c-411e-419f-baf9-12dcffc029e8" />


--
# Auteur
Mehdi Bouabid
Étudiant en Génie Informatique — EST Meknès
# Encadrement
Projet réalisé sous l’encadrement de Ahmad Amar.
# Licence
Projet académique réalisé dans le cadre d’un PFA.
