# User CRUD API Documentation

## 📋 Overview

This API provides complete CRUD (Create, Read, Update, Delete) operations for managing users in the system. All data is stored in a SQLite database.

## 🔗 Base URL

```
http://localhost:8080/api
```

## 📚 API Endpoints

### 1. Get All Users

**GET** `/users`

Retrieve a list of all users in the system.

**Query Parameters:**
- `activeOnly` (optional, boolean): Filter only active users
- `search` (optional, string): Search users by name

**Example Request:**
```bash
curl -X GET http://localhost:8080/api/users
```

**Example Response:**
```json
{
  "count": 5,
  "message": "Users retrieved successfully",
  "users": [
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "phone": "+1234567890",
      "birthDate": "1990-05-15",
      "gender": "Male",
      "address": "123 Main Street",
      "city": "New York",
      "country": "USA",
      "postalCode": "10001",
      "bio": "Software engineer passionate about technology.",
      "avatarUrl": null,
      "isActive": true,
      "createdAt": "2025-11-10T13:37:22.031",
      "updatedAt": "2025-11-10T13:37:22.031",
      "fullName": "John Doe"
    }
  ]
}
```

---

### 2. Get User by ID

**GET** `/users/{id}`

Retrieve details of a specific user.

**Path Parameters:**
- `id` (required, Long): User ID

**Example Request:**
```bash
curl -X GET http://localhost:8080/api/users/1
```

**Example Response:**
```json
{
  "message": "User retrieved successfully",
  "user": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "+1234567890",
    "birthDate": "1990-05-15",
    "gender": "Male",
    "address": "123 Main Street",
    "city": "New York",
    "country": "USA",
    "postalCode": "10001",
    "bio": "Software engineer passionate about technology.",
    "avatarUrl": null,
    "isActive": true,
    "createdAt": "2025-11-10T13:37:22.031",
    "updatedAt": "2025-11-10T13:37:22.031",
    "fullName": "John Doe"
  }
}
```

**Error Response (404):**
```json
{
  "timestamp": "2025-11-10T13:40:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with id: 999"
}
```

---

### 3. Create New User

**POST** `/users`

Create a new user in the system.

**Request Body:**
```json
{
  "firstName": "Alice",
  "lastName": "Anderson",
  "email": "alice.anderson@example.com",
  "phone": "+1555123456",
  "birthDate": "1993-04-25",
  "gender": "Female",
  "address": "999 Cherry Lane",
  "city": "San Francisco",
  "country": "USA",
  "postalCode": "94102",
  "bio": "Marketing specialist with expertise in digital campaigns."
}
```

**Required Fields:**
- `firstName` (string, max 50 chars)
- `lastName` (string, max 50 chars)
- `email` (string, valid email format, unique, max 100 chars)

**Optional Fields:**
- `phone` (string, max 20 chars)
- `birthDate` (date, format: YYYY-MM-DD)
- `gender` (string, max 10 chars)
- `address` (string, max 255 chars)
- `city` (string, max 50 chars)
- `country` (string, max 50 chars)
- `postalCode` (string, max 10 chars)
- `bio` (string, max 500 chars)
- `avatarUrl` (string)

**Example Request:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Alice",
    "lastName": "Anderson",
    "email": "alice.anderson@example.com",
    "phone": "+1555123456",
    "birthDate": "1993-04-25",
    "gender": "Female",
    "city": "San Francisco",
    "country": "USA"
  }'
```

**Success Response (201):**
```json
{
  "message": "User created successfully",
  "user": {
    "id": 6,
    "firstName": "Alice",
    "lastName": "Anderson",
    "email": "alice.anderson@example.com",
    "isActive": true,
    "createdAt": "2025-11-10T13:38:04.454",
    "updatedAt": "2025-11-10T13:38:04.454"
  }
}
```

**Error Response (409 - Duplicate Email):**
```json
{
  "timestamp": "2025-11-10T13:40:00",
  "status": 409,
  "error": "Conflict",
  "message": "User already exists with email: alice.anderson@example.com"
}
```

**Validation Error (400):**
```json
{
  "timestamp": "2025-11-10T13:40:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Input validation failed",
  "validationErrors": {
    "firstName": "First name is required",
    "email": "Email should be valid"
  }
}
```

---

### 4. Update User (Full Update)

**PUT** `/users/{id}`

Update all fields of an existing user.

**Path Parameters:**
- `id` (required, Long): User ID

**Request Body:** (All fields required)
```json
{
  "firstName": "Alice",
  "lastName": "Anderson-Updated",
  "email": "alice.anderson@example.com",
  "phone": "+1555999888",
  "birthDate": "1993-04-25",
  "gender": "Female",
  "address": "999 Cherry Lane Apt 5",
  "city": "San Francisco",
  "country": "USA",
  "postalCode": "94102",
  "bio": "Senior marketing specialist."
}
```

**Example Request:**
```bash
curl -X PUT http://localhost:8080/api/users/6 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Alice",
    "lastName": "Anderson-Updated",
    "email": "alice.anderson@example.com",
    "phone": "+1555999888"
  }'
```

**Success Response (200):**
```json
{
  "message": "User updated successfully",
  "user": {
    "id": 6,
    "firstName": "Alice",
    "lastName": "Anderson-Updated",
    "updatedAt": "2025-11-10T13:45:00"
  }
}
```

---

### 5. Partial Update User

**PATCH** `/users/{id}`

Update only specific fields of an existing user.

**Path Parameters:**
- `id` (required, Long): User ID

**Request Body:** (Only include fields to update)
```json
{
  "phone": "+1555999888",
  "bio": "Updated bio text"
}
```

**Example Request:**
```bash
curl -X PATCH http://localhost:8080/api/users/6 \
  -H "Content-Type: application/json" \
  -d '{"phone": "+1555999888"}'
```

---

### 6. Delete User

**DELETE** `/users/{id}`

Permanently delete a user from the system (hard delete).

**Path Parameters:**
- `id` (required, Long): User ID

**Example Request:**
```bash
curl -X DELETE http://localhost:8080/api/users/6
```

**Success Response (200):**
```json
{
  "message": "User deleted successfully",
  "deletedUserId": 6
}
```

**Error Response (404):**
```json
{
  "timestamp": "2025-11-10T13:40:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with id: 999"
}
```

---

## 🔍 Additional Endpoints

### 7. Search Users (Advanced)

**GET** `/users/search`

Search users with multiple criteria.

**Query Parameters:**
- `firstName` (optional, string)
- `lastName` (optional, string)
- `email` (optional, string)
- `city` (optional, string)
- `isActive` (optional, boolean)

**Example Request:**
```bash
curl -X GET "http://localhost:8080/api/users/search?city=Bangkok&isActive=true"
```

---

### 8. Get User by Email

**GET** `/users/email/{email}`

Retrieve user by email address.

**Example Request:**
```bash
curl -X GET http://localhost:8080/api/users/email/john.doe@example.com
```

---

### 9. Deactivate User (Soft Delete)

**POST** `/users/{id}/deactivate`

Deactivate a user (soft delete - sets isActive to false).

**Example Request:**
```bash
curl -X POST http://localhost:8080/api/users/1/deactivate
```

---

### 10. Activate User

**POST** `/users/{id}/activate`

Reactivate a previously deactivated user.

**Example Request:**
```bash
curl -X POST http://localhost:8080/api/users/1/activate
```

---

### 11. Get User Statistics

**GET** `/users/stats`

Get statistics about users in the system.

**Example Request:**
```bash
curl -X GET http://localhost:8080/api/users/stats
```

**Example Response:**
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

---

## 💾 Database Schema

### User Table Fields

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | Auto | Primary key |
| firstName | String(50) | Yes | User's first name |
| lastName | String(50) | Yes | User's last name |
| email | String(100) | Yes | User's email (unique) |
| phone | String(20) | No | Phone number |
| birthDate | Date | No | Date of birth |
| gender | String(10) | No | Gender |
| address | String(255) | No | Street address |
| city | String(50) | No | City |
| country | String(50) | No | Country |
| postalCode | String(10) | No | Postal/ZIP code |
| bio | String(500) | No | User biography |
| avatarUrl | String | No | Avatar image URL |
| isActive | Boolean | Yes | Active status (default: true) |
| createdAt | DateTime | Auto | Creation timestamp |
| updatedAt | DateTime | Auto | Last update timestamp |

---

## 🔐 Error Codes

| Code | Description |
|------|-------------|
| 200 | OK - Request successful |
| 201 | Created - User created successfully |
| 400 | Bad Request - Validation failed |
| 404 | Not Found - User not found |
| 409 | Conflict - Duplicate email |
| 500 | Internal Server Error |

---

## 🧪 Testing with curl

### Get all users
```bash
curl http://localhost:8080/api/users
```

### Get user by ID
```bash
curl http://localhost:8080/api/users/1
```

### Create user
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"test@example.com"}'
```

### Update user
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Updated","lastName":"Name","email":"updated@example.com"}'
```

### Delete user
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

---

## 📝 Notes

- All timestamps are in UTC timezone
- The SQLite database file (`database.db`) is created automatically in the project root
- Sample data is loaded automatically on first startup
- Email addresses must be unique across all users
- Soft delete (deactivate) is recommended over hard delete for data integrity

---

## 🚀 Getting Started

1. Start the application:
```bash
./mvnw spring-boot:run
```

2. Access the API at:
```
http://localhost:8080/api/users
```

3. Check health status:
```bash
curl http://localhost:8080/actuator/health
```

Enjoy using the User CRUD API! 🎉