# Gestion Employee Backend (Tests d'Integration)

Ceci est un projet backend pour la gestion des employés et des départements. Il utilise Spring Boot pour créer une API REST permettant de gérer les employés et les départements.

## Table des matières

- [Gestion Employee Backend](#gestion-employee-backend)
    - [Table des matières](#table-des-matières)
    - [Aperçu](#aperçu)
        - [Fonctionnalités](#fonctionnalités)
    - [Tests](#tests)
        - [Tests du Contrôleur Employee](#tests-du-contrôleur-employee)
        - [Tests du Répository Employee](#tests-du-répository-employee)
        - [Tests du Service Employee](#tests-du-service-employee)
    - [Auteur](#auteur)

## Aperçu

### Fonctionnalités

Les utilisateurs peuvent :
- Consulter tous les employés
- Consulter un employé par son identifiant
- Ajouter un nouvel employé
- Supprimer un employé
- Consulter tous les départements
- Consulter un département par son identifiant
- Ajouter un nouveau département
- Supprimer un département


## Tests

Les tests sont une partie cruciale de ce projet. Ils assurent que les fonctionnalités de base fonctionnent correctement et permettent de vérifier que les modifications n'introduisent pas de régressions.

### Tests du Contrôleur Employee

- Vérifie que les endpoints de l'API fonctionnent comme prévu.
- Exemples de tests :
    - Retourner tous les employés.
    - Retourner un employé par son identifiant.
    - Ajouter un nouvel employé.
    - Supprimer un employé.

### Tests du Répository Employee

- Vérifie que les opérations CRUD fonctionnent correctement.
- Exemples de tests :
    - Trouver un employé par son identifiant.
    - Enregistrer un nouvel employé.
    - Supprimer un employé.

### Tests du Service Employee

- Vérifie la logique métier et les opérations de service.
- Exemples de tests :
    - Obtenir un employé par son identifiant.
    - Ajouter un nouvel employé.
    - Supprimer un employé.

## Auteur

- GitHub - [@ikitamalarose](https://github.com/ikitamalarose)
- Twitter - [@ikitamalarose](https://www.twitter.com/ikitamalarose)
- Email - [laroseikitama@gmail.com](mailto:laroseikitama@gmail.com)

