# EBanking API - Architecture et Guide de Développement

## 🎯 Objectif : Séparation stricte des couches

Cette application suit un modèle en couches avec une séparation **stricte et incontournable** des responsabilités.
Chaque couche a un rôle unique et des règles de communication définies.

---

## 📐 Architecture en couches

### 1️⃣ **Application Layer (Couche Application)**

**Responsabilité:** Réception des requêtes et préparation des réponses

**Composants:**

- **Controllers** : Reçoivent les requêtes HTTP
- **Handlers** : Gèrent les erreurs spécifiques à l'API (REST exceptions)
- **Mappers** : Convertissent `DTO ↔ Domain Models`
- **DTOs** : Objets de transfert de données

**Règles strictes:**

- ✅ Manipule **UNIQUEMENT les DTOs** en entrée/sortie
- ✅ Communique avec la Service Layer via des **interfaces (`IXxxService`)**
- ❌ N'accède **JAMAIS directement** aux entités ou repositories
- ❌ Ne contient **JAMAIS de logique métier**
- ❌ N'utilise **JAMAIS les Domain Models** directement

**Flux:** `HTTP Request` → `Controller` → `DTO Mapper` → `IXxxService` → `HTTP Response`

---

### 2️⃣ **Domain Layer (Couche Métier)**

**Responsabilité:** Logique métier et modèles de domaine

**Composants:**

- **Models** : Objets métier (ex: `Customer`, `Account`, `Transaction`)
- **Services** : Interfaces des services métier (`ICustomerService`, `IAccountService`)
- **Handlers** : Gèrent les événements métier
- **Exceptions** : Exceptions métier spécifiques

**Règles strictes:**

- ✅ Indépendant des frameworks (Spring, JPA, etc.)
- ✅ Contient **la logique métier pure**
- ✅ Communique avec Persistence Layer via des **interfaces (`IXxxPersistenceService`)**
- ❌ N'utilise **JAMAIS les DTOs**
- ❌ N'utilise **JAMAIS les Entities JPA**

**Exemple:**

```java
public interface ICustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long id);

    void updateCustomer(Customer customer);
}
```

---

### 3️⃣ **Infrastructure Layer (Couche Techniques)**

**Responsabilité:** Accès aux données, configuration, sécurité

**Composants:**

- **Persistence Services** : Implémentations concrètes de `IXxxPersistenceService`
- **Repositories** : Accès direct à la base de données (Spring Data JPA)
- **Mappers** : Convertissent `Entity ↔ Domain Models`
- **Config** : Configuration Spring, bases de données
- **Security** : Composants de sécurité, authentification
- **Handlers** : Gèrent les interactions avec systèmes externes

**Règles strictes:**

- ✅ Manipule **UNIQUEMENT les Entities JPA**
- ✅ Implémente les interfaces du Domain Layer
- ✅ Communique avec Repository via des interfaces
- ❌ N'expose **JAMAIS les Entities** aux couches supérieures
- ❌ Ne contient **JAMAIS de logique métier** (sauf conversion/mapping)

**Flux:** `IXxxPersistenceService` → `Persistence Mapper` → `IXxxRepository` → `Entity` → `Database`

---

### 4️⃣ **Shared Layer (Couche Transversale)**

**Responsabilité:** Utilitaires et composants partagés

**Composants:**

- **Utils** : Fonctions utilitaires
- **Constants** : Constantes globales

---

## 📁 Structure de fichiers

```
src/main/java/org/msd/ebankingbackend/
│
├── application/                          # 🌐 Couche Application
│   ├── controller/
│   │   ├── CustomerController.java
│   │   ├── AccountController.java
│   │   ├── TransactionController.java
│   │   └── AddressController.java
│   ├── handler/
│   │   ├── GlobalExceptionHandler.java  # Gère les exceptions REST
│   │   └── ApiErrorResponse.java
│   ├── mapper/
│   │   ├── ICustomerApplicationMapper.java
│   │   ├── CustomerApplicationMapper.java
│   │   ├── IAccountApplicationMapper.java
│   │   ├── AccountApplicationMapper.java
│   │   ├── ITransactionApplicationMapper.java
│   │   ├── TransactionApplicationMapper.java
│   │   ├── IAddressApplicationMapper.java
│   │   └── AddressApplicationMapper.java
│   └── dto/
│       ├── request/
│       │   ├── CreateCustomerRequestDto.java
│       │   ├── UpdateCustomerRequestDto.java
│       │   ├── CreateAccountRequestDto.java
│       │   ├── CreateTransactionRequestDto.java
│       │   └── CreateAddressRequestDto.java
│       └── response/
│           ├── CustomerResponseDto.java
│           ├── AccountResponseDto.java
│           ├── TransactionResponseDto.java
│           └── AddressResponseDto.java
│
├── domain/                               # 💼 Couche Métier
│   ├── model/
│   │   ├── Customer.java
│   │   ├── Account.java
│   │   ├── Transaction.java
│   │   ├── Address.java
│   │   └── Contact.java
│   ├── service/
│   │   ├── ICustomerService.java        # Interface
│   │   ├── IAccountService.java
│   │   ├── ITransactionService.java
│   │   └── IAddressService.java
│   ├── handler/
│   │   └── DomainEventHandler.java      # Événements métier
│   └── exception/
│       ├── CustomerNotFoundException.java
│       ├── AccountNotFoundException.java
│       ├── InsufficientFundsException.java
│       └── BusinessException.java
│
├── infrastructure/                       # 🔧 Couche Infrastructure
│   ├── persistence/
│   │   ├── service/
│   │   │   ├── ICustomerPersistenceService.java
│   │   │   ├── CustomerPersistenceService.java
│   │   │   ├── IAccountPersistenceService.java
│   │   │   ├── AccountPersistenceService.java
│   │   │   ├── ITransactionPersistenceService.java
│   │   │   ├── TransactionPersistenceService.java
│   │   │   ├── IAddressPersistenceService.java
│   │   │   └── AddressPersistenceService.java
│   │   ├── mapper/
│   │   │   ├── ICustomerPersistenceMapper.java
│   │   │   ├── CustomerPersistenceMapper.java
│   │   │   ├── IAccountPersistenceMapper.java
│   │   │   ├── AccountPersistenceMapper.java
│   │   │   ├── ITransactionPersistenceMapper.java
│   │   │   ├── TransactionPersistenceMapper.java
│   │   │   ├── IAddressPersistenceMapper.java
│   │   │   └── AddressPersistenceMapper.java
│   │   └── repository/
│   │       ├── ICustomerRepository.java
│   │       ├── IAccountRepository.java
│   │       ├── ITransactionRepository.java
│   │       └── IAddressRepository.java
│   ├── entity/
│   │   ├── CustomerEntity.java
│   │   ├── AccountEntity.java
│   │   ├── TransactionEntity.java
│   │   ├── AddressEntity.java
│   │   └── ContactEntity.java
│   ├── config/
│   │   ├── DatabaseConfig.java
│   │   ├── JpaConfig.java
│   │   └── MapStructConfig.java
│   ├── security/
│   │   ├── JwtTokenProvider.java
│   │   ├── SecurityConfig.java
│   │   └── CustomUserDetailsService.java
│   └── handler/
│       └── ExternalServiceHandler.java
│
└── shared/                               # 📦 Couche Partagée
    └── util/
        ├── DateUtils.java
        ├── ValidationUtils.java
        └── Constants.java
```

---

## 🔄 Flux de communication inter-couches

### Exemple: Créer un client

```
HTTP POST /api/customers
  ↓
CustomerController.createCustomer(CreateCustomerRequestDto)
  ↓
CustomerApplicationMapper.toModel(CreateCustomerRequestDto) → Customer (Model)
  ↓
ICustomerService.createCustomer(Customer)
  ↓
ICustomerPersistenceService.save(Customer)
  ↓
CustomerPersistenceMapper.toEntity(Customer) → CustomerEntity
  ↓
ICustomerRepository.save(CustomerEntity)
  ↓
Database
```

**Retour:**

```
CustomerEntity → CustomerPersistenceMapper → Customer (Model)
  ↓
ICustomerService → CustomerApplicationMapper → CustomerResponseDto
  ↓
HTTP 201 Created + CustomerResponseDto
```

---

## ✅ Règles d'or (Non négociables)

### Application Layer

- ❌ Pas de `@Entity`, pas de JPA
- ❌ Pas de logique métier directe
- ✅ Utilise uniquement les DTOs et interfaces de services
- ✅ Tous les paramètres des méthodes sont des DTOs

### Domain Layer

- ✅ Logique métier PURE
- ❌ Aucune dépendance Spring, JPA ou Framework
- ❌ Pas de DTOs, pas d'Entities
- ✅ Tous les paramètres sont des Domain Models

### Infrastructure Layer

- ✅ Manipulation des Entities
- ✅ Implémentation concrète des interfaces du Domain
- ❌ Pas d'exposition des Entities aux couches supérieures
- ❌ Pas de logique métier
- ✅ Utilise les Repositories pour accéder aux données

### Dépendances autorisées (Dependency Inversion)

```
Application Layer
    ↓ (dépend de)
Domain Layer (interfaces + models)
    ↓ (dépend de)
Infrastructure Layer (implémente les interfaces)

⚠️ JAMAIS de remontée de dépendance!
⚠️ JAMAIS de cross-layer dependencies!
```

---

## 🛠️ Principes SOLID appliqués

### Single Responsibility Principle (SRP)

- Chaque classe a **UNE seule raison de changer**
- Controllers: gèrent les requêtes HTTP
- Services: gèrent la logique métier
- Persistence: gèrent l'accès aux données
- Mappers: font uniquement de la conversion

### Open-Closed Principle (OCP)

- Les interfaces permettent l'extension sans modification
- Ex: `ICustomerService` peut avoir plusieurs implémentations

### Liskov Substitution Principle (LSP)

- Les implémentations d'interfaces respectent le contrat
- `CustomerService implements ICustomerService` fonctionne partout où `ICustomerService` est attendu

### Interface Segregation Principle (ISP)

- Interfaces spécifiques et restreintes
- `ICustomerPersistenceService` expose UNIQUEMENT les méthodes de persistence
- `ICustomerService` expose UNIQUEMENT les méthodes métier

### Dependency Inversion Principle (DIP)

- Injection par constructeur via interfaces
- Jamais de dépendance directe sur les classes concrètes
- Les couches supérieures ne connaissent que les interfaces

---

## 📋 Checklist de validation

Avant de valider un code:

- [ ] Les controllers utilisent **UNIQUEMENT les DTOs** en paramètre et retour
- [ ] Les services du Domain utilisent **UNIQUEMENT les Domain Models**
- [ ] Les repositories manipulent **UNIQUEMENT les Entities**
- [ ] Tous les mappers convertissent correctement les types
- [ ] Toutes les dépendances sont injectées par **constructeur**
- [ ] Pas de logique métier dans les controllers ou persistence
- [ ] Les interfaces `I*Service` et `I*PersistenceService` sont toujours présentes
- [ ] Les Entities JPA ne remontent **JAMAIS** aux couches supérieures
- [ ] Aucun appel direct entre controllers et repositories
- [ ] Aucun appel direct entre controllers et entities
- [ ] Les mappers sont utilisés à chaque conversion de type

---

## 🚀 Points clés à retenir

1. **Isolation des responsabilités** → Chaque couche a un rôle unique et incontournable
2. **Interfaces comme contrats** → Facilite les tests et l'évolution
3. **Mappers pour la conversion** → Jamais de conversion directe ou implicite
4. **No cross-layer dependencies** → Pas de remontée de dépendance, direction stricte
5. **DTOs pour la communication externe** → Jamais les modèles métier
6. **Entities = Base de données** → Jamais exposées au-delà de la Persistence
7. **Domain Models = Logique métier** → Jamais de frameworks, jamais d'annotations Spring/JPA

---

## 📚 Ressources et exemples

Pour chaque nouvelle entité (ex: `Customer`):

1. **Domain Model** → `domain/model/Customer.java`
2. **Service Interface** → `domain/service/ICustomerService.java`
3. **Persistence Interface** → `infrastructure/persistence/service/ICustomerPersistenceService.java`
4. **Persistence Implementation** → `infrastructure/persistence/service/CustomerPersistenceService.java`
5. **Persistence Mapper Interface** → `infrastructure/persistence/mapper/ICustomerPersistenceMapper.java`
6. **Persistence Mapper Implementation** → `infrastructure/persistence/mapper/CustomerPersistenceMapper.java`
7. **Entity** → `infrastructure/entity/CustomerEntity.java`
8. **Repository Interface** → `infrastructure/persistence/repository/ICustomerRepository.java`
9. **Application Mapper Interface** → `application/mapper/ICustomerApplicationMapper.java`
10. **Application Mapper Implementation** → `application/mapper/CustomerApplicationMapper.java`
11. **Request DTO** → `application/dto/request/CreateCustomerRequestDto.java`
12. **Response DTO** → `application/dto/response/CustomerResponseDto.java`
13. **Controller** → `application/controller/CustomerController.java`

