# User Management Backend with MySQL - PRODIGY_BD_02

A RESTful API for user management built with Spring Boot, Java, and MySQL database for persistent storage.

## Features
- Create, Read, Update, Delete (CRUD) operations for users
- RESTful API endpoints
- MySQL database for persistent storage
- JPA/Hibernate ORM integration
- Connection pooling with HikariCP
- Environment-specific configurations
- Database migrations
- JSON request/response format

## Technologies Used
- Java 21
- Spring Boot 3.3.0
- Spring Data JPA
- MySQL 8.0+
- HikariCP (Connection Pooling)
- Maven
- Environment Variables (.env)

## Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.6+

## Setup Instructions

### 1. Database Setup
```sql
CREATE DATABASE user_management;
```

### 2. Environment Configuration
Create `.env` file in project root:
```
DB_HOST=localhost
DB_PORT=3306
DB_NAME=user_management
DB_USERNAME=root
DB_PASSWORD=your_password
DB_POOL_SIZE=10
```

### 3. Run Application
```bash
mvn spring-boot:run
```

## Database Schema
The application automatically creates the `users` table with:
- `id` (BINARY(16)) - Primary Key (UUID)
- `name` (VARCHAR(255)) - User name
- `email` (VARCHAR(255)) - Unique email
- `age` (INT) - User age

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users` | Get all users |
| POST | `/users` | Create a new user |
| GET | `/users/{id}` | Get user by ID |
| PUT | `/users/{id}` | Update user by ID |
| DELETE | `/users/{id}` | Delete user by ID |

## Request/Response Format

### Create User (POST /users)
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 25
}
```

### Response
```json
{
  "id": "generated-uuid",
  "name": "John Doe",
  "email": "john@example.com",
  "age": 25
}
```

## How to Run

1. Ensure Java 17+ is installed
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```
3. Access the API at `http://localhost:8080`

## Technologies Used
- Java 25
- Spring Boot 3.3.0
- Maven
- H2 Database (in-memory)

## Testing
Use tools like Postman, Thunder Client, or curl to test the API endpoints.