# 🏛️ Clean Architecture Implementation

## Overview

This project has been refactored to follow **Clean Architecture** principles, ensuring separation of concerns, testability, and maintainability.

---

## 📐 Architecture Layers

### 1. **Domain Layer** (Core Business Logic)
- **Location:** `domain/`
- **Purpose:** Contains business entities and business rules
- **No Dependencies:** This layer has no dependencies on outer layers
- **Components:**
  - `model/User.java` - Pure business entity with business methods
  - `repository/UserRepository.java` - Repository interface (contract)
  - `usecase/UserUseCase.java` - Use case interface defining business operations

### 2. **Application Layer** (Use Cases & DTOs)
- **Location:** `application/`
- **Purpose:** Orchestrates data flow between layers
- **Components:**
  - `dto/UserDTO.java` - Data Transfer Object for input
  - `dto/UserResponseDTO.java` - Data Transfer Object for output
  - `mapper/UserMapper.java` - Maps between Domain Model and DTOs
  - `service/UserUseCaseImpl.java` - Implementation of use cases

### 3. **Infrastructure Layer** (External Interfaces)
- **Location:** `infrastructure/`
- **Purpose:** Implements technical details (database, external APIs)
- **Components:**
  - `persistence/entity/UserEntity.java` - JPA entity (database model)
  - `persistence/repository/JpaUserRepository.java` - Spring Data JPA repository
  - `persistence/mapper/UserEntityMapper.java` - Maps between Domain and JPA Entity
  - `persistence/adapter/UserRepositoryAdapter.java` - Implements domain repository interface
  - `config/DataLoader.java` - Initial data loading

### 4. **Presentation Layer** (Controllers & UI)
- **Location:** `presentation/`
- **Purpose:** Handles HTTP requests/responses
- **Components:**
  - `controller/UserController.java` - REST API endpoints
  - `controller/HelloWorldController.java` - Basic endpoints

### 5. **Cross-Cutting Concerns**
- **Location:** Root packages
- **Components:**
  - `exception/` - Custom exceptions and global exception handler
  - `HelloWorldBackendApplication.java` - Application entry point

---

## 🔄 Data Flow

```
┌─────────────────────────────────────────────────────────────┐
│                     HTTP Request                             │
│                         ↓                                    │
│  ┌────────────────────────────────────────────────────┐    │
│  │  Presentation Layer (Controller)                    │    │
│  │  - Receives HTTP request                            │    │
│  │  - Validates input                                  │    │
│  │  - Converts DTO to Domain Model                     │    │
│  └──────────────────────┬──────────────────────────────┘    │
│                         ↓                                    │
│  ┌────────────────────────────────────────────────────┐    │
│  │  Application Layer (Use Case)                       │    │
│  │  - Executes business logic                          │    │
│  │  - Coordinates between domain and infrastructure    │    │
│  └──────────────────────┬──────────────────────────────┘    │
│                         ↓                                    │
│  ┌────────────────────────────────────────────────────┐    │
│  │  Domain Layer (Business Logic)                      │    │
│  │  - Contains business rules                          │    │
│  │  - Pure business entities                           │    │
│  └──────────────────────┬──────────────────────────────┘    │
│                         ↓                                    │
│  ┌────────────────────────────────────────────────────┐    │
│  │  Infrastructure Layer (Data Access)                 │    │
│  │  - Repository Adapter                               │    │
│  │  - JPA Repository                                   │    │
│  │  - Database (SQLite)                                │    │
│  └──────────────────────┬──────────────────────────────┘    │
│                         ↓                                    │
│                   HTTP Response                              │
└─────────────────────────────────────────────────────────────┘
```

---

## 📦 Package Structure

```
com.workshop4.helloworldbackend/
├── domain/                           # Domain Layer (Core)
│   ├── model/
│   │   └── User.java                # Domain Entity
│   ├── repository/
│   │   └── UserRepository.java      # Repository Interface
│   └── usecase/
│       └── UserUseCase.java         # Use Case Interface
│
├── application/                      # Application Layer
│   ├── dto/
│   │   ├── UserDTO.java            # Input DTO
│   │   └── UserResponseDTO.java    # Output DTO
│   ├── mapper/
│   │   └── UserMapper.java         # DTO ↔ Domain Mapper
│   └── service/
│       └── UserUseCaseImpl.java    # Use Case Implementation
│
├── infrastructure/                   # Infrastructure Layer
│   ├── persistence/
│   │   ├── entity/
│   │   │   └── UserEntity.java     # JPA Entity
│   │   ├── repository/
│   │   │   └── JpaUserRepository.java  # Spring Data JPA
│   │   ├── mapper/
│   │   │   └── UserEntityMapper.java   # Domain ↔ Entity Mapper
│   │   └── adapter/
│   │       └── UserRepositoryAdapter.java # Repository Implementation
│   └── config/
│       └── DataLoader.java          # Data Initialization
│
├── presentation/                     # Presentation Layer
│   └── controller/
│       ├── UserController.java      # REST API
│       └── HelloWorldController.java
│
├── exception/                        # Cross-Cutting
│   ├── ResourceNotFoundException.java
│   ├── DuplicateResourceException.java
│   └── GlobalExceptionHandler.java
│
└── HelloWorldBackendApplication.java # Main Class
```

---

## 🎯 Design Principles

### Dependency Rule
**Dependencies point inward:**
- Presentation → Application → Domain
- Infrastructure → Domain (through interfaces)
- Domain has NO dependencies on outer layers

### Separation of Concerns
- **Domain:** Business logic only
- **Application:** Use case orchestration
- **Infrastructure:** Technical implementation
- **Presentation:** HTTP/API concerns

### Dependency Inversion
- Domain defines interfaces (UserRepository, UserUseCase)
- Infrastructure implements these interfaces
- Application & Presentation depend on abstractions, not concretions

---

## 🔑 Key Components Explained

### Domain Model (User.java)
```java
// Pure business entity - no framework dependencies
public class User {
    private Long id;
    private String firstName;
    // ... other fields
    
    // Business methods
    public void activate() { ... }
    public void deactivate() { ... }
    public void addPoints(Integer points) { ... }
}
```

### Use Case Interface (UserUseCase.java)
```java
// Defines what the system can do
public interface UserUseCase {
    User createUser(User user);
    User getUserById(Long id);
    // ... other operations
}
```

### Repository Interface (UserRepository.java)
```java
// Defines data access contract
public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    // ... other methods
}
```

### Repository Adapter (UserRepositoryAdapter.java)
```java
// Implements domain repository using JPA
@Component
public class UserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository jpaRepository;
    private final UserEntityMapper mapper;
    
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return mapper.toDomainModel(saved);
    }
}
```

### Controller (UserController.java)
```java
// Handles HTTP, uses DTOs
@RestController
public class UserController {
    private final UserUseCase userUseCase;
    private final UserMapper userMapper;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserDTO dto) {
        User user = userMapper.toDomainModel(dto);
        User created = userUseCase.createUser(user);
        UserResponseDTO response = userMapper.toResponseDTO(created);
        return ResponseEntity.ok(response);
    }
}
```

---

## ✨ Benefits

### 1. **Testability**
- Each layer can be tested independently
- Domain logic can be tested without database or HTTP
- Easy to mock dependencies

### 2. **Maintainability**
- Clear separation of concerns
- Easy to locate and modify code
- Changes in one layer don't affect others

### 3. **Flexibility**
- Easy to swap implementations (e.g., change database)
- Can add new features without modifying existing code
- Framework-independent business logic

### 4. **Scalability**
- Layers can be developed by different teams
- Easy to add new use cases
- Can evolve infrastructure without touching business logic

### 5. **Framework Independence**
- Domain layer has no Spring dependencies
- Can migrate to different frameworks easily
- Business logic survives technology changes

---

## 🔧 How to Extend

### Adding a New Use Case
1. Add method to `UserUseCase.java` interface
2. Implement in `UserUseCaseImpl.java`
3. Add endpoint in `UserController.java`
4. Create DTOs if needed

### Adding a New Entity
1. Create domain model in `domain/model/`
2. Create repository interface in `domain/repository/`
3. Create JPA entity in `infrastructure/persistence/entity/`
4. Create JPA repository in `infrastructure/persistence/repository/`
5. Create adapter in `infrastructure/persistence/adapter/`
6. Create use case interface and implementation
7. Create DTOs and mappers
8. Create controller

### Changing Database
1. Replace JPA with new technology in `infrastructure/persistence/`
2. Update `UserRepositoryAdapter` implementation
3. Domain and Application layers remain unchanged!

---

## 📚 Additional Resources

- [Clean Architecture by Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/)
- [Domain-Driven Design](https://martinfowler.com/bliki/DomainDrivenDesign.html)

---

## 🎓 Migration from Old Structure

### Old Structure Issues:
- ❌ Controller depends directly on Service
- ❌ Service depends directly on JPA Entity
- ❌ Business logic mixed with infrastructure
- ❌ Hard to test without database

### New Structure Benefits:
- ✅ Clear layer separation
- ✅ Domain independent of frameworks
- ✅ Easy to test each layer
- ✅ Can change infrastructure without affecting business logic
- ✅ Follows SOLID principles

---

**Clean Architecture ensures your codebase remains maintainable and flexible as it grows!** 🚀
