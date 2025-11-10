# Java Spring Boot Hello World Backend

A simple Java Spring Boot backend application that provides "Hello World" REST API endpoints.

## Prerequisites

- Java 17 or higher
- Maven 3.6+ or newer
- (Optional) IDE like IntelliJ IDEA, Eclipse, or VS Code

## Project Structure

```
Workshop4/
├── pom.xml
├── README.md
├── run.sh
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── workshop4/
    │   │           └── helloworldbackend/
    │   │               ├── HelloWorldBackendApplication.java
    │   │               └── controller/
    │   │                   └── HelloWorldController.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/
            └── com/
                └── workshop4/
                    └── helloworldbackend/
                        └── HelloWorldBackendApplicationTests.java
```

## Available API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/` | API information and available endpoints |
| GET | `/api/hello` | Returns Hello World message |
| GET | `/api/hello/{name}` | Returns personalized Hello message |
| GET | `/actuator/health` | Application health check |

## How to Build and Run

### Method 1: Using Maven Wrapper (Recommended)

1. **Make the Maven wrapper executable:**
   ```bash
   chmod +x mvnw
   ```

2. **Build the project:**
   ```bash
   ./mvnw clean package
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

### Method 2: Using Maven (if installed globally)

1. **Build the project:**
   ```bash
   mvn clean package
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

### Method 3: Running the JAR file

1. **Build the JAR:**
   ```bash
   ./mvnw clean package
   ```

2. **Run the JAR:**
   ```bash
   java -jar target/hello-world-backend-1.0.0.jar
   ```

### Method 4: Using the run script

```bash
chmod +x run.sh
./run.sh
```

## Testing the API

Once the application is running (default port 8080), you can test the endpoints:

### Using curl:

```bash
# Basic hello endpoint
curl http://localhost:8080/api/hello

# Personalized hello
curl http://localhost:8080/api/hello/YourName

# API info
curl http://localhost:8080/api/

# Health check
curl http://localhost:8080/actuator/health
```

### Using web browser:
- Open `http://localhost:8080/api/hello` in your browser
- Open `http://localhost:8080/api/hello/YourName` in your browser
- Open `http://localhost:8080/api/` for API documentation

## Expected Responses

### GET /api/hello
```json
{
  "message": "Hello World from Java Spring Boot!",
  "status": "success",
  "timestamp": "2025-11-10T10:30:45.123456Z"
}
```

### GET /api/hello/John
```json
{
  "message": "Hello John from Java Spring Boot!",
  "status": "success",
  "timestamp": "2025-11-10T10:30:45.123456Z"
}
```

## Running Tests

```bash
./mvnw test
```

## Configuration

The application can be configured through `src/main/resources/application.properties`:

- `server.port=8080` - Change the server port
- `logging.level.com.workshop4.helloworldbackend=INFO` - Adjust logging level

## Stopping the Application

Press `Ctrl+C` in the terminal where the application is running.

## Development

To modify the application:

1. Edit the controller in `src/main/java/com/workshop4/helloworldbackend/controller/HelloWorldController.java`
2. Modify application settings in `src/main/resources/application.properties`
3. Rebuild and restart the application

## Troubleshooting

1. **Port 8080 already in use**: Change the port in `application.properties` to `server.port=8081`
2. **Java version issues**: Make sure you have Java 17 or higher installed
3. **Maven issues**: Use the Maven wrapper (`./mvnw`) instead of global Maven

Enjoy your Java Spring Boot Hello World Backend! 🚀