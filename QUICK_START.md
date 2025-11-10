# 🚀 Quick Start Guide

เริ่มต้นใช้งาน User Management API ได้ในไม่กี่นาที!

## ⚡ Quick Setup

### 1. Clone & Navigate
```bash
cd Workshop4
```

### 2. Build & Run
```bash
./mvnw spring-boot:run
```

### 3. Verify
```bash
curl http://localhost:8080/actuator/health
```

✅ คุณพร้อมใช้งานแล้ว!

---

## 🎯 ตัวอย่างการใช้งาน

### 📋 ดูรายการผู้ใช้ทั้งหมด

```bash
curl http://localhost:8080/api/users | jq .
```

**Expected Output:**
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
      "isActive": true
    }
  ]
}
```

---

### 👤 ดูข้อมูลผู้ใช้คนเดียว

```bash
curl http://localhost:8080/api/users/1 | jq .
```

---

### ➕ สร้างผู้ใช้ใหม่

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "สมศรี",
    "lastName": "ดีงาม",
    "email": "somsri@example.com",
    "phone": "082-111-2222",
    "membershipLevel": "Silver",
    "points": 3000
  }' | jq .
```

**Expected Output:**
```json
{
  "message": "User created successfully",
  "user": {
    "id": 6,
    "firstName": "สมศรี",
    "lastName": "ดีงาม",
    "email": "somsri@example.com",
    "phone": "082-111-2222",
    "membershipLevel": "Silver",
    "points": 3000,
    "isActive": true
  }
}
```

---

### ✏️ แก้ไขข้อมูลผู้ใช้

```bash
curl -X PUT http://localhost:8080/api/users/6 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "สมศรี",
    "lastName": "ดีงาม-Updated",
    "email": "somsri@example.com",
    "phone": "082-333-4444",
    "membershipLevel": "Gold",
    "points": 10000
  }' | jq .
```

---

### 🗑️ ลบผู้ใช้

```bash
curl -X DELETE http://localhost:8080/api/users/6 | jq .
```

**Expected Output:**
```json
{
  "message": "User deleted successfully",
  "deletedUserId": 6
}
```

---

### 📊 ดูสถิติผู้ใช้

```bash
curl http://localhost:8080/api/users/stats | jq .
```

**Expected Output:**
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

### 🔍 ค้นหาผู้ใช้

```bash
# ค้นหาตามเมือง
curl "http://localhost:8080/api/users/search?city=กรุงเทพมหานคร" | jq .

# ค้นหาเฉพาะผู้ใช้ที่ active
curl "http://localhost:8080/api/users/search?isActive=true" | jq .

# ค้นหาตามชื่อ
curl "http://localhost:8080/api/users/search?firstName=สมชาย" | jq .
```

---

### 📧 ค้นหาด้วยอีเมล

```bash
curl http://localhost:8080/api/users/email/somchai@example.com | jq .
```

---

### ⏸️ Deactivate ผู้ใช้ (Soft Delete)

```bash
curl -X POST http://localhost:8080/api/users/1/deactivate | jq .
```

---

### ▶️ Activate ผู้ใช้

```bash
curl -X POST http://localhost:8080/api/users/1/activate | jq .
```

---

### 🔄 Partial Update (PATCH)

อัปเดตเฉพาะฟิลด์ที่ต้องการเปลี่ยน:

```bash
curl -X PATCH http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "points": 20000,
    "membershipLevel": "Platinum"
  }' | jq .
```

---

## 🎨 ตัวอย่าง Postman Collection

### Base URL
```
http://localhost:8080
```

### Request Examples

#### 1. Get All Users
- **Method:** GET
- **URL:** `/api/users`

#### 2. Create User
- **Method:** POST
- **URL:** `/api/users`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "firstName": "ทดสอบ",
  "lastName": "ระบบ",
  "email": "test@example.com",
  "phone": "088-888-8888",
  "membershipLevel": "Bronze",
  "points": 1000
}
```

#### 3. Update User
- **Method:** PUT
- **URL:** `/api/users/1`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "firstName": "สมชาย",
  "lastName": "ใจดี-Updated",
  "email": "somchai@example.com",
  "phone": "081-234-5678",
  "membershipLevel": "Platinum",
  "points": 25000
}
```

#### 4. Delete User
- **Method:** DELETE
- **URL:** `/api/users/6`

---

## 🔥 Advanced Examples

### Filtering Active Users
```bash
curl "http://localhost:8080/api/users?activeOnly=true" | jq .
```

### Search by Name
```bash
curl "http://localhost:8080/api/users?search=สมชาย" | jq .
```

### Get Only Specific Fields
```bash
curl http://localhost:8080/api/users | jq '.users[] | {firstName, lastName, email, membershipLevel, points}'
```

### Count Users
```bash
curl http://localhost:8080/api/users | jq '.count'
```

### Sort Users by Points (using jq)
```bash
curl http://localhost:8080/api/users | jq '.users | sort_by(.points) | reverse'
```

---

## 💡 Tips & Tricks

### 1. Pretty Print JSON
```bash
# Add | jq . to any curl command
curl http://localhost:8080/api/users | jq .
```

### 2. Save Response to File
```bash
curl http://localhost:8080/api/users > users.json
```

### 3. Check Response Status
```bash
curl -i http://localhost:8080/api/users/1
```

### 4. Verbose Mode (see full request/response)
```bash
curl -v http://localhost:8080/api/users/1
```

### 5. Timing Requests
```bash
time curl http://localhost:8080/api/users
```

---

## 🐛 Common Issues

### 1. Connection Refused
**Problem:** `curl: (7) Failed to connect to localhost port 8080`

**Solution:**
```bash
# Check if application is running
lsof -i :8080

# If not running, start it
./mvnw spring-boot:run
```

### 2. 404 Not Found
**Problem:** User ID doesn't exist

**Solution:**
```bash
# Check available user IDs first
curl http://localhost:8080/api/users | jq '.users[].id'
```

### 3. 409 Conflict
**Problem:** Email already exists

**Solution:** Use a different email address when creating users

### 4. 400 Bad Request
**Problem:** Invalid JSON or missing required fields

**Solution:** Verify JSON format and ensure firstName, lastName, and email are provided

---

## 🎓 Learning Path

1. ✅ **Start Here:** Test GET endpoints to see existing data
2. ✅ **Create:** Practice POST to create new users
3. ✅ **Update:** Try PUT and PATCH to modify users
4. ✅ **Delete:** Learn DELETE and soft delete (deactivate)
5. ✅ **Search:** Explore search and filter capabilities
6. ✅ **Advanced:** Use query parameters and complex filters

---

## 📚 Next Steps

- Read [USER_API_DOCUMENTATION.md](USER_API_DOCUMENTATION.md) for complete API reference
- See [README.md](README.md) for full project documentation
- Explore the source code in `src/main/java/com/workshop4/helloworldbackend/`

---

**Happy Coding! 🚀**
