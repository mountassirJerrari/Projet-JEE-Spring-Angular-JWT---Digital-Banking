# Digital Banking System

This is a monorepo containing the Digital Banking system components.

## Project Structure

- **backend/** - Spring Boot backend application
  - REST API for banking operations
  - JPA entities for Customer, BankAccount, and AccountOperation
  - H2 database for development

## Backend API Endpoints

The backend exposes the following REST endpoints:

- `GET /accounts` - Get all accounts
- `GET /accounts/{id}` - Get account by ID
- `GET /accounts/{id}/operations` - Get account operations
- `GET /accounts/{id}/pageOperations` - Get paginated account operations
- `POST /accounts/debit` - Debit an account
- `POST /accounts/credit` - Credit an account
- `POST /accounts/transfer` - Transfer between accounts

## Running the Backend

```bash
cd backend
./mvnw spring-boot:run
```

The backend will be available at http://localhost:8080.
