EBanking Api

1. Respecter une séparation stricte entre les couches
   Architecture suggérée :
   Controller Layer (API)

Responsable de la réception des requêtes et de la préparation des réponses.
Communique exclusivement avec le Service Layer via des DTOs.
Service Layer (Business Logic)

Gère la logique métier.
Ne manipule que des Domain Models (ou DTOs) et communique avec le Persistence Layer via des interfaces.
Persistence Layer (Data Access)

Responsable de toutes les interactions avec la base de données.
Expose des méthodes abstraites et claires via des interfaces.
Ne retourne jamais d'entités directement à la couche supérieure, mais utilise des Domain Models.
Mapping Layer

Utilise des mappers comme MapStruct pour convertir entre Domain Models, Entities, et DTOs.
Domain Layer

Contient les objets métier et la logique qui leur est propre.
Indépendant des frameworks (Spring, JPA, etc.).

2. Structuration du code
   src/main/java/com/example/project

├── application
│ ├── controller
│ ├── handler # Handlers liés à l'API (par ex., exceptions REST)
│ ├── mapper
│ └── dto
├── domain
│ ├── model
│ ├── service
│ ├── handler # Handlers d'événements métier
│ └── exception
├── infrastructure
│ ├── persistence
│ ├── handler # Handlers techniques (systèmes externes)
│ ├── security # Composants de sécurité
│ ├── config
│ └── mapper
├── shared
│ └── util
