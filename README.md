# WinWin Travel: Backend Test Task

This project consists of two microservices (Auth-API and Data-API) demonstrating JWT authentication, inter-service communication via Feign Client, and PostgreSQL integration.

## Running the Application

The entire system is containerized. You can build and start all services (Postgres, Service A, Service B) with a single command:

```bash
docker compose up --build
```

Note: The database will be automatically initialized with required tables using the init.sql script.

Testing the API (CURL Examples)
1. Register a new user
```bash
curl -X POST http://localhost:8080/api/auth/register ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"user@test.com\",\"password\":\"pass123\"}"
```

2. Login (Get JWT Token)
```bash
  curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"user@test.com\",\"password\":\"pass123\"}"
```
Copy the token value from the JSON response.

3. Process Data (Protected Route)
Replace <TOKEN> with your actual JWT:
```bash
curl -X POST http://localhost:8080/api/process ^
  -H "Authorization: Bearer <TOKEN>" ^
  -H "Content-Type: application/json" ^
  -d "{\"text\":\"hello world\"}"
```

  Project Structure
/authapi - Service A (Authentication, Logging, Feign Client)

/dataapi - Service B (Data Transformation)

docker-compose.yml - Infrastructure orchestration

init.sql - Database schema initialization
