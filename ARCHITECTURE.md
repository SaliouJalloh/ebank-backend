# 🏗️ Architecture EBanking - Guide complet

## 🚦 Règles d'architecture (STRICTES ET INCONTOURNABLES)

### Couche Application 🌐

✅ Utilise **UNIQUEMENT les DTOs**
✅ Communique avec les services via des **interfaces (`I*Service`)**
✅ Mappe les DTOs vers les modèles métier et vice-versa
❌ Jamais de logique métier
❌ Jamais d'accès direct aux repositories ou entities
❌ Jamais de `@Entity` ou annotations JPA

### Couche Métier 💼

✅ Contient la **logique métier PURE**
✅ Manipule les **Domain Models**
✅ Communique avec la persistence via des **interfaces (`I*PersistenceService`)**
❌ Aucune dépendance Spring ou JPA
❌ Pas de DTOs
❌ Pas d'Entities

### Couche Infrastructure 🔧

✅ Manipule **UNIQUEMENT les Entities** pour la base de données
✅ Implémente les interfaces du Domain Layer
✅ Fait les conversions `Entity ↔ Model` via des **Mappers**
❌ Jamais d'exposition des Entities aux couches supérieures
❌ Pas de logique métier (sauf conversion/mapping)
❌ Pas de DTOs

### Couche Partagée 📦

✅ Utilitaires, constantes, outils
✅ Accessible à toutes les couches
❌ Aucune logique complexe

---

## 📋 Conventions de nommage

| Composant                 | Niveau         | Suffixe              | Interface              | Exemple                       |
|---------------------------|----------------|----------------------|------------------------|-------------------------------|
| **Controller**            | Application    | `Controller`         | Optionnel              | `CustomerController`          |
| **DTO Request**           | Application    | `RequestDto`         | ❌                      | `CreateCustomerRequestDto`    |
| **DTO Response**          | Application    | `ResponseDto`        | ❌                      | `CustomerResponseDto`         |
| **Mapper (App)**          | Application    | `ApplicationMapper`  | `I*ApplicationMapper`  | `CustomerApplicationMapper`   |
| **Service Interface**     | Domain         | `Service`            | `I*Service`            | `ICustomerService`            |
| **Persistence Interface** | Domain         | `PersistenceService` | `I*PersistenceService` | `ICustomerPersistenceService` |
| **Persistence Impl**      | Infrastructure | `PersistenceService` | ✅ implémente interface | `CustomerPersistenceService`  |
| **Mapper (Persistence)**  | Infrastructure | `PersistenceMapper`  | `I*PersistenceMapper`  | `CustomerPersistenceMapper`   |
| **Repository**            | Infrastructure | `Repository`         | `I*Repository`         | `ICustomerRepository`         |
| **Entity**                | Infrastructure | `Entity`             | ❌                      | `CustomerEntity`              |

---

## ✅ Injection de dépendances

### Toujours par constructeur

```java

@Component
public class CustomerPersistenceService implements ICustomerPersistenceService {
    private final ICustomerRepository repository;
    private final ICustomerPersistenceMapper mapper;

    // ✅ Injection par constructeur
    public CustomerPersistenceService(
            ICustomerRepository repository,
            ICustomerPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
```

### Jamais d'injection par setter ou attribut

```java
❌MAUVAIS:
@Autowired
private ICustomerRepository repository;

❌MAUVAIS:

@Autowired
public void setRepository(ICustomerRepository repository) { ...}
```

---

## 🔄 Flux de conversion des types

### Scénario: Créer un Customer

```
HTTP Request (JSON)
    ↓
CreateCustomerRequestDto (Application Layer)
    ↓ [CustomerApplicationMapper::toModel]
Customer (Domain Model)
    ↓ [ICustomerService::createCustomer]
ICustomerPersistenceService::save(Customer)
    ↓ [CustomerPersistenceMapper::toEntity]
CustomerEntity (Infrastructure)
    ↓ [JpaRepository::save]
Database
```

### Scénario: Récupérer un Customer

```
Database
    ↓
CustomerEntity (Infrastructure)
    ↓ [CustomerPersistenceMapper::toModel]
Customer (Domain Model)
    ↓ [ICustomerService::getCustomerById]
Controller reçoit Customer
    ↓ [CustomerApplicationMapper::toResponseDto]
CustomerResponseDto (Application Layer)
    ↓
HTTP Response (JSON)
```

---

## 🎯 Classes concrètes vs Interfaces

### Services du Domain (Business Logic)

```java
// ✅ Interface (dans domain/service/)
public interface ICustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long id);
}

// ❌ Pas d'interface pour l'implémentation concrète
// ❌ Jamais créer CustomerService
// L'interface est implémentée par la couche Application
```

### Services de Persistence

```java
// ✅ Interface (dans domain/ ou infrastructure/)
public interface ICustomerPersistenceService {
    Customer save(Customer customer);

    Optional<Customer> findById(Long id);
}

// ✅ Implémentation concrète (dans infrastructure/persistence/)
@Component
public class CustomerPersistenceService implements ICustomerPersistenceService {
    // Implémentation
}
```

### Repositories JPA

```java
// ✅ Interface (dans infrastructure/persistence/repository/)
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
    // Méthodes custom si nécessaire
}

// ❌ Jamais d'implémentation manuelle
```

### Mappers

```java
// ✅ Interface
public interface ICustomerApplicationMapper {
    Customer toModel(CreateCustomerRequestDto dto);

    CustomerResponseDto toResponseDto(Customer model);
}

// ✅ Implémentation (MapStruct)
@Mapper(componentModel = "spring")
public class CustomerApplicationMapper implements ICustomerApplicationMapper {
    // Implémentation MapStruct
}
```

---

## 📁 Structure attendue pour chaque entité

Pour ajouter une nouvelle entité (ex: `Product`):

```
1. domain/model/Product.java
2. domain/service/IProductService.java
3. domain/exception/ProductNotFoundException.java
4. 
5. infrastructure/persistence/service/IProductPersistenceService.java
6. infrastructure/persistence/service/ProductPersistenceService.java
7. infrastructure/persistence/mapper/IProductPersistenceMapper.java
8. infrastructure/persistence/mapper/ProductPersistenceMapper.java
9. infrastructure/persistence/repository/IProductRepository.java
10. infrastructure/entity/ProductEntity.java
11.
12. application/dto/request/CreateProductRequestDto.java
13. application/dto/request/UpdateProductRequestDto.java
14. application/dto/response/ProductResponseDto.java
15. application/mapper/IProductApplicationMapper.java
16. application/mapper/ProductApplicationMapper.java
17. application/controller/ProductController.java
```

---

## ❌ Anti-patterns à éviter ABSOLUMENT

### 1. ❌ Exposer les Entities

```java
// MAUVAIS
@GetMapping("/{id}")
public CustomerEntity getCustomer(@PathVariable Long id) {
    return repository.findById(id);
}

// BON
@GetMapping("/{id}")
public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id) {
    Customer customer = service.getCustomerById(id);
    return ResponseEntity.ok(mapper.toResponseDto(customer));
}
```

### 2. ❌ Logique métier dans le Controller

```java
// MAUVAIS
@PostMapping
public ResponseEntity<CustomerResponseDto> create(@RequestBody CreateCustomerRequestDto dto) {
    if (dto.getAge() < 18) {
        throw new InvalidAgeException();
    }
    // Validation métier dans le controller
    Customer customer = new Customer(...);
    return ResponseEntity.ok(mapper.toResponseDto(customer));
}

// BON: La validation métier est dans le service
@PostMapping
public ResponseEntity<CustomerResponseDto> create(@RequestBody CreateCustomerRequestDto dto) {
    Customer customer = mapper.toModel(dto);
    customer = service.createCustomer(customer);  // Logique métier ici
    return ResponseEntity.ok(mapper.toResponseDto(customer));
}
```

### 3. ❌ Dépendance circulaire entre les couches

```java
// MAUVAIS
// Service (Domain) qui appelle un Controller
@Service
public class CustomerService {
    @Autowired
    private CustomerController controller;  // ❌ INTERDIT
}

// BON: Hiérarchie stricte sans remontée
Controller →Service →PersistenceService →Repository
```

### 4. ❌ Mapper implicite ou absence de mapper

```java
// MAUVAIS
Customer customer = (Customer) dto;  // Cast implicite
Customer customer = new Customer(dto);  // Copie implicite

// BON
Customer customer = mapper.toModel(dto);  // Conversion explicite via mapper
```

### 5. ❌ DTOs dans les services métier

```java
// MAUVAIS
public interface ICustomerService {
    CustomerResponseDto createCustomer(CreateCustomerRequestDto dto);
}

// BON
public interface ICustomerService {
    Customer createCustomer(Customer customer);  // Domain Models uniquement
}
```

### 6. ❌ Entities dans les services métier

```java
// MAUVAIS
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository repository;

    public Customer createCustomer(Customer customer) {
        CustomerEntity entity = repository.save(...);  // ❌ Expose Entity
        return entity;
    }
}

// BON
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerPersistenceService persistenceService;

    public Customer createCustomer(Customer customer) {
        return persistenceService.save(customer);  // ✅ Domain Model
    }
}
```

---

## 🧪 Tests

### Tests unitaires des Services (Domain)

```java

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private ICustomerPersistenceService persistenceService;
    @InjectMocks
    private CustomerService service;

    @Test
    public void testCreateCustomer() {
        // Arrange
        Customer customer = new Customer("John", "Doe");

        // Act
        service.createCustomer(customer);

        // Assert
        verify(persistenceService).save(customer);
    }
}
```

### Tests d'intégration des Controllers

```java

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateCustomer() {
        CreateCustomerRequestDto dto = new CreateCustomerRequestDto("John", "Doe");

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}
```

---

## 📊 Diagramme des dépendances

```
┌─────────────────────────────────────────────────────────┐
│                  APPLICATION LAYER 🌐                   │
│  Controllers, DTOs, Mappers (App), Handlers (API)       │
│              ↓ (depends on interfaces)                   │
├─────────────────────────────────────────────────────────┤
│                   DOMAIN LAYER 💼                        │
│  Models, Service Interfaces, Exceptions, Handlers       │
│              ↓ (depends on interfaces)                   │
├─────────────────────────────────────────────────────────┤
│              INFRASTRUCTURE LAYER 🔧                     │
│ PersistenceService, Mappers (Persistence), Repositories,│
│               Entities, Config, Security                │
├─────────────────────────────────────────────────────────┤
│                  SHARED LAYER 📦                         │
│            Utils, Constants, Common Tools               │
└─────────────────────────────────────────────────────────┘

⚠️ Les dépendances remontent UNIQUEMENT par les interfaces
⚠️ Jamais de cross-layer calls directs
⚠️ Jamais de remontée entre les couches
```

---

## 🎯 Métriques de qualité

Pour vérifier l'architecture:

- [ ] Zéro import d'Entities dans les Services
- [ ] Zéro import de DTOs dans les Services
- [ ] Zéro import de Controllers dans les Services
- [ ] Zéro logique métier dans les Controllers
- [ ] Zéro logique métier dans la Persistence
- [ ] 100% des dépendances via interfaces
- [ ] 100% de conversion via Mappers
- [ ] Injection uniquement par constructeur

---

## 🚀 Commandes utiles

```bash
# Vérifier les violations d'architecture
mvn clean test

# Analyser la qualité du code
mvn sonar:sonar

# Générer les rapports de couverture
mvn jacoco:report
```
