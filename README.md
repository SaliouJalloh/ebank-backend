EBanking Api

## Respecter une séparation stricte entre les couches

Architecture de l'application :

    1. Controller Layer (API)

- Responsable de la réception des requêtes et de la préparation des réponses.

- Communique exclusivement avec le Service Layer via des DTOs.

      2. Service Layer (Business Logic)

- Gère la logique métier.
- Ne manipule que des Domain Models (ou DTOs) et communique avec le Persistence Layer via des interfaces.

      3. Persistence Layer (Data Access)

- Responsable de toutes les interactions avec la base de données.
- Expose des méthodes abstraites et claires via des interfaces.
- Ne retourne jamais d'entités directement à la couche supérieure, mais utilise des Domain Models.

      4. Mapping Layer

- Utilise des mappers comme MapStruct pour convertir entre Domain Models, Entities, et DTOs.

      5. Domain Layer

- Contient les objets métier et la logique qui leur est propre.
- Indépendant des frameworks (Spring, JPA, etc.).

## Structuration du code

- src/main/java/com/example/project

```
application
├── controller
├── handler # Handlers liés à l'API (par ex., exceptions REST)
├── mapper
├── dto
domain
├── model
├── service
├── handler # Handlers d'événements métier
├── exception
infrastructure
├── persistence
├── handler # Handlers techniques (systèmes externes)
├── security # Composants de sécurité
├── config
├── mapper
shared
└── util
```

## SOLID Principles dans chaque couche

3.1 Single Responsibility Principle (SRP)

- Chaque classe ou composant doit avoir une responsabilité unique.

- Par exemple :

- Les mappers doivent uniquement convertir entre les modèles.
- Les services doivent uniquement contenir la logique métier.

3.2 Open-Closed Principle (OCP)

- Facilitez l'extension sans modifier le code existant.
- Par exemple :
- Utilisez des interfaces dans le Service Layer pour permettre une implémentation différente si besoin (mock pour les
  tests ou extension future).

3.3 Liskov Substitution Principle (LSP)

- Les sous-classes ou implémentations doivent pouvoir remplacer leurs super-classes sans altérer le comportement.
- Par exemple :
- Les implémentations de UserPersistenceService doivent se conformer à l’interface et ne pas exposer de logique
  inattendue.

3.4 Interface Segregation Principle (ISP)

- Favorisez des interfaces spécifiques et restreintes.
- Par exemple :
- Une interface UserRepository devrait exposer uniquement les méthodes nécessaires à l'application (et non toutes les
  méthodes génériques inutiles de JPA).

3.5 Dependency Inversion Principle (DIP)

- Les couches supérieures ne doivent pas dépendre directement des couches inférieures mais d'abstractions (interfaces).
- Par exemple :
- Injectez les dépendances via les interfaces (UserRepository, UserPersistenceMapper) dans les services.

### Résumé des bonnes pratiques

1. handler:

- Spécifique à l'API (erreurs REST) ​​: application/handler.

- Métier (événements du domaine) : domain/handler.

- Systèmes externes ou techniques : infra/handler.

2. Security :

Toujours dans infra/security, car la sécurité est une préoccupation infrastructurelle.

Cette organisation garantit une séparation claire des responsabilités et respecte les principes de modularité et de
SOLID .