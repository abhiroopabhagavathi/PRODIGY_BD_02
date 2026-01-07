# User Management Backend - PRODIGY_BD_01

A RESTful API for user management built with Spring Boot and Java.

## Features
- Create, Read, Update, Delete (CRUD) operations for users
- RESTful API endpoints
- In-memory data storage
- JSON request/response format

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