# 🚀 Java Spring Boot User Management API

A complete RESTful API backend application built with Spring Boot for managing user profiles with membership system integration. Features full CRUD operations, SQLite database, and LBK Membership tracking.

---

## 📖 Documentation

- 🚀 **[Quick Start Guide](QUICK_START.md)** - Get started in minutes with examples
- 📚 **[API Documentation](USER_API_DOCUMENTATION.md)** - Complete API reference
- 📋 **[README](README.md)** - Full project documentation (this file)

---

## ✨ Features

- ✅ Complete User CRUD operations (Create, Read, Update, Delete)
- ✅ SQLite database integration with JPA/Hibernate
- ✅ LBK Membership system with tiers (Gold, Platinum, Silver, Bronze)
- ✅ Points/Rewards tracking system
- ✅ User profile management with comprehensive fields
- ✅ Global exception handling
- ✅ Input validation with Jakarta Bean Validation
- ✅ RESTful API design with JSON responses
- ✅ Sample data pre-loading
- ✅ Health check endpoint
- ✅ CORS enabled for frontend integration

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+ or newer
- (Optional) IDE like IntelliJ IDEA, Eclipse, or VS Code
- (Optional) curl or Postman for API testing

## 📁 Project Structure

```
Workshop4/
├── pom.xml
├── README.md
├── USER_API_DOCUMENTATION.md
├── database.db (SQLite database - auto-generated)
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── workshop4/
    │   │           └── helloworldbackend/
    │   │               ├── HelloWorldBackendApplication.java
    │   │               ├── config/
    │   │               │   └── DataLoader.java
    │   │               ├── controller/
    │   │               │   ├── HelloWorldController.java
    │   │               │   └── UserController.java
    │   │               ├── entity/
    │   │               │   └── User.java
    │   │               ├── exception/
    │   │               │   ├── DuplicateResourceException.java
    │   │               │   ├── GlobalExceptionHandler.java
    │   │               │   └── ResourceNotFoundException.java
    │   │               ├── repository/
    │   │               │   └── UserRepository.java
    │   │               └── service/
    │   │                   └── UserService.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/
            └── com/
                └── workshop4/
                    └── helloworldbackend/
                        └── HelloWorldBackendApplicationTests.java
```

## 🌐 Available API Endpoints

### Basic Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/hello` | Returns Hello World message |
| GET | `/actuator/health` | Application health check |

### User Management Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users (with optional filters) |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/email/{email}` | Get user by email |
| GET | `/api/users/search` | Search users with criteria |
| GET | `/api/users/stats` | Get user statistics |
| POST | `/api/users` | Create new user |
| PUT | `/api/users/{id}` | Update user (full update) |
| PATCH | `/api/users/{id}` | Partial update user |
| DELETE | `/api/users/{id}` | Delete user (hard delete) |
| POST | `/api/users/{id}/activate` | Activate user account |
| POST | `/api/users/{id}/deactivate` | Deactivate user (soft delete) |

📖 **For detailed API documentation, see [USER_API_DOCUMENTATION.md](USER_API_DOCUMENTATION.md)**

## 🔧 User Entity Fields

The User entity includes comprehensive profile information and LBK Membership features:

### Basic Information
- `id` - Unique identifier (auto-generated)
- `memberId` - LBK Member ID (e.g., LBK001234)
- `firstName` - ชื่อ (required, max 50 chars)
- `lastName` - นามสกุล (required, max 50 chars)
- `email` - อีเมล (required, unique, validated)
- `phone` - เบอร์โทรศัพท์ (max 20 chars)

### Personal Details
- `birthDate` - วันเกิด (Date format: YYYY-MM-DD)
- `gender` - เพศ (max 10 chars)
- `address` - ที่อยู่ (max 255 chars)
- `city` - เมือง (max 50 chars)
- `country` - ประเทศ (max 50 chars)
- `postalCode` - รหัสไปรษณีย์ (max 10 chars)
- `bio` - ประวัติส่วนตัว (max 500 chars)
- `avatarUrl` - URL รูปโปรไฟล์

### LBK Membership System
- `membershipLevel` - ระดับสมาชิก (Gold, Platinum, Silver, Bronze)
- `points` - แต้มคงเหลือ (Integer, default: 0)
- `registrationDate` - วันที่สมัครสมาชิก

### System Fields
- `isActive` - สถานะการใช้งาน (Boolean, default: true)
- `createdAt` - วันที่สร้าง (auto-generated)
- `updatedAt` - วันที่อัปเดตล่าสุด (auto-updated)

## 🚀 How to Build and Run

### Method 1: Using Maven Wrapper (Recommended)

1. **Build the project:**
   ```bash
   ./mvnw clean compile
   ```

2. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

### Method 2: Running JAR file

1. **Build the JAR:**
   ```bash
   ./mvnw clean package
   ```

2. **Run the JAR:**
   ```bash
   java -jar target/hello-world-backend-1.0.0.jar
   ```

### Method 3: Background execution

```bash
nohup ./mvnw spring-boot:run > app.log 2>&1 &
```

## 🧪 Testing the API

Once the application is running (default port: **8080**), test the endpoints:

### Quick Tests with curl:

```bash
# 1. Health check
curl http://localhost:8080/actuator/health

# 2. Get all users
curl http://localhost:8080/api/users

# 3. Get user by ID
curl http://localhost:8080/api/users/1

# 4. Get user statistics
curl http://localhost:8080/api/users/stats

# 5. Create new user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "สมศักดิ์",
    "lastName": "ใจดี",
    "email": "somsak@example.com",
    "phone": "089-999-8888",
    "membershipLevel": "Gold",
    "points": 5000
  }'

# 6. Update user
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "สมชาย",
    "lastName": "ใจดี-Updated",
    "email": "somchai@example.com",
    "phone": "081-234-5678"
  }'

# 7. Delete user
curl -X DELETE http://localhost:8080/api/users/6
```

### Using web browser:
- **API Base:** `http://localhost:8080/api`
- **All Users:** `http://localhost:8080/api/users`
- **User Stats:** `http://localhost:8080/api/users/stats`
- **Health Check:** `http://localhost:8080/actuator/health`

## 📊 Sample Data

The application automatically loads 5 sample users on first startup:

1. **สมชาย ใจดี** (LBK001234) - Gold member, 15,420 points
2. **สมหญิง รักดี** (LBK001235) - Platinum member, 28,750 points
3. **วิชัย มั่นคง** (LBK001236) - Silver member, 5,680 points
4. **กานต์ธิดา สวยงาม** (LBK001237) - Bronze member, 1,200 points (inactive)
5. **ธนากร เจริญสุข** (LBK001238) - Gold member, 12,890 points

## 📤 Example API Responses

### GET /api/users (Get all users)
```json
{
  "count": 5,
  "message": "Users retrieved successfully",
  "users": [
    {
      "id": 1,
      "memberId": "LBK001234",
      "firstName": "สมชาย",
      "lastName": "ใจดี",
      "email": "somchai@example.com",
      "phone": "081-234-5678",
      "membershipLevel": "Gold",
      "points": 15420,
      "registrationDate": "2023-06-15",
      "isActive": true,
      "createdAt": "2025-11-10T13:46:20",
      "updatedAt": "2025-11-10T13:46:20"
    }
  ]
}
```

### POST /api/users (Create user)
```json
{
  "message": "User created successfully",
  "user": {
    "id": 6,
    "memberId": null,
    "firstName": "สมศักดิ์",
    "lastName": "ใจดี",
    "email": "somsak@example.com",
    "phone": "089-999-8888",
    "membershipLevel": "Gold",
    "points": 5000,
    "isActive": true,
    "createdAt": "2025-11-10T13:50:00",
    "updatedAt": "2025-11-10T13:50:00"
  }
}
```

### GET /api/users/stats
```json
{
  "stats": {
    "totalUsers": 5,
    "activeUsers": 4,
    "inactiveUsers": 1
  },
  "message": "User statistics retrieved successfully"
}
```

### GET /api/hello
```json
{
  "message": "hello world"
}
```

## 🗄️ Database

### SQLite Configuration

The application uses SQLite as the embedded database:

- **Database File:** `database.db` (auto-created in project root)
- **JPA/Hibernate:** Auto-creates tables on first run
- **DDL Mode:** `update` (preserves data on restart)
- **Show SQL:** Enabled in logs for debugging

### Database Schema

The `users` table is automatically created with the following structure:

```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    member_id VARCHAR(20) UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    birth_date DATE,
    gender VARCHAR(10),
    address VARCHAR(255),
    city VARCHAR(50),
    country VARCHAR(50),
    postal_code VARCHAR(10),
    bio VARCHAR(500),
    avatar_url TEXT,
    membership_level VARCHAR(20),
    points INTEGER DEFAULT 0,
    registration_date DATE,
    is_active BOOLEAN DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Reset Database

To reset the database with fresh sample data:

```bash
# Stop the application
# Delete the database file
rm -f database.db

# Restart the application - it will create new database with sample data
./mvnw spring-boot:run
```

## ⚙️ Configuration

The application can be configured through `src/main/resources/application.properties`:

### Server Settings
```properties
server.port=8080
```

### Database Settings
```properties
spring.datasource.url=jdbc:sqlite:database.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Logging Settings
```properties
logging.level.com.workshop4.helloworldbackend=INFO
logging.level.org.springframework.web=INFO
logging.level.org.hibernate.SQL=DEBUG
```

## 🧪 Running Tests

```bash
./mvnw test
```

## 🛑 Stopping the Application

### If running in foreground:
Press `Ctrl+C` in the terminal

### If running in background:
```bash
# Find the process
lsof -i :8080

# Kill the process (replace PID with actual process ID)
kill <PID>
```

## 🔧 Development

### Adding New Features

1. **Add new entity fields:** Edit `User.java`
2. **Create new endpoints:** Edit `UserController.java`
3. **Add business logic:** Edit `UserService.java`
4. **Customize queries:** Edit `UserRepository.java`
5. **Modify sample data:** Edit `DataLoader.java`

### Project Reload

After making changes:
```bash
# Recompile
./mvnw clean compile

# Restart application
./mvnw spring-boot:run
```

## 🐛 Troubleshooting

### Port 8080 already in use
```bash
# Option 1: Find and kill the process
lsof -i :8080
kill <PID>

# Option 2: Change port in application.properties
server.port=8081
```

### Database locked error
```bash
# Delete the database file and restart
rm -f database.db
./mvnw spring-boot:run
```

### Java version issues
```bash
# Check Java version (must be 17+)
java -version

# If version is wrong, install Java 17 or higher
```

### Maven issues
```bash
# Make Maven wrapper executable
chmod +x mvnw

# Clean and rebuild
./mvnw clean install
```

### Dependency issues
```bash
# Force update dependencies
./mvnw clean install -U
```

## 📚 Technology Stack

- **Framework:** Spring Boot 3.3.5
- **Language:** Java 17
- **Build Tool:** Maven 3.9+
- **Database:** SQLite 3.46+
- **ORM:** Spring Data JPA + Hibernate
- **Validation:** Jakarta Bean Validation
- **Logging:** SLF4J + Logback
- **API:** RESTful JSON APIs
- **Testing:** JUnit 5

## 📖 Additional Resources

- **Detailed API Docs:** [USER_API_DOCUMENTATION.md](USER_API_DOCUMENTATION.md)
- **Spring Boot Docs:** https://spring.io/projects/spring-boot
- **Spring Data JPA:** https://spring.io/projects/spring-data-jpa
- **SQLite Docs:** https://www.sqlite.org/docs.html

## 📝 License

This project is created for educational purposes.

---

**Developed with ❤️ using Spring Boot and Java**

Enjoy your User Management API! 🎉