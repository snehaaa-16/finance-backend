# Finance Backend

A secure Finance Management Backend built using Java Spring Boot.  
This project provides APIs for managing financial records with authentication, filtering, pagination, soft delete, and summary insights.

---

## Tech Stack

| Layer | Technology |
|------|-----------|
| Language | Java 17 |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Build Tool | Maven |
| Utilities | Lombok |

---

## Project Structure

```
src/main/java/com/sneha/finance/
├── controller/        # FinanceController, AuthController
├── service/
│   ├── interfaces/    # FinanceService
│   └── impl/          # FinanceServiceImpl
├── repository/        # FinancialRecordRepository
├── entity/            # FinancialRecord, BaseEntity
├── dto/               # Request & Response DTOs
├── security/          # JWT related classes
├── exception/         # GlobalExceptionHandler
└── enums/             # Category, RecordType
```

---

## Features

- JWT Authentication  
- Financial Records CRUD APIs  
- Filtering (type, category, date range)  
- Pagination and sorting  
- Soft delete (using `deleted_at`)  
- Financial summary API (income, expense, balance)  
- Global exception handling  
- Standard API response format  

---

## Setup Instructions

### Prerequisites
- Java 17+
- Maven
- PostgreSQL

### 1. Create Database

```sql
CREATE DATABASE finance_db;
```

### 2. Configure `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/finance_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run Application

```bash
mvn spring-boot:run
```

Application runs on:  
👉 http://localhost:8080

---

## Authentication

Call login API:

```
POST /api/auth/login
```

Use JWT token in headers:

```
Authorization: Bearer <token>
```

---

## API Endpoints

### Records

```
POST   /api/records
GET    /api/records
GET    /api/records/filter
PUT    /api/records/{id}
DELETE /api/records/{id}
```

### Summary

```
GET /api/records/summary
```

---

## Sample Response

```json
{
  "success": true,
  "data": {
    "totalIncome": 5000.0,
    "totalExpense": 1500.0,
    "balance": 3500.0
  },
  "message": "Summary fetched successfully"
}
```

---

## Key Design Decisions

- Soft delete implemented using `deleted_at` field instead of physical deletion  
- Database-level filtering using JPA queries for better performance  
- Layered architecture (Controller → Service → Repository)  
- Consistent API response using a generic wrapper  

---

## Assumptions

- JWT is required for all protected APIs  
- Deleted records are excluded from queries  
- Default pagination: `page=0`, `size=5`  

---

## Author

**Sneha Jaiswal**
