# Digital Banking Application - Project Report

## Project Overview

This project is a comprehensive Digital Banking application developed using Spring Boot for the backend and Angular for the frontend. The application provides a secure and feature-rich platform for managing banking operations, including customer management, account management, and transaction processing.

## Table of Contents

1. [Architecture](#architecture)
2. [Technologies Used](#technologies-used)
3. [Features](#features)
4. [Security Implementation](#security-implementation)
5. [Backend Implementation](#backend-implementation)
6. [Frontend Implementation](#frontend-implementation)
7. [Database Design](#database-design)
8. [API Documentation](#api-documentation)
9. [Installation and Setup](#installation-and-setup)
10. [Future Enhancements](#future-enhancements)

## Architecture

The application follows a modern microservices architecture with a clear separation between the frontend and backend:

- **Backend**: RESTful API built with Spring Boot
- **Frontend**: Single Page Application (SPA) built with Angular
- **Database**: H2 in-memory database (can be easily switched to MySQL, PostgreSQL, etc.)
- **Authentication**: JWT (JSON Web Token) based authentication

The architecture ensures:
- Loose coupling between components
- Scalability
- Maintainability
- Security

## Technologies Used

### Backend
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Security 6 with JWT
- Lombok
- MapStruct
- Maven

### Frontend
- Angular 17
- TypeScript
- Bootstrap 5
- HTML5/CSS3
- JWT Authentication

## Features

### Customer Management
- Create new customers
- View customer details
- Update customer information
- Delete customers
- Search for customers by name

### Account Management
- Create different types of accounts (Current, Saving)
- View account details
- Search accounts by balance range
- Search accounts by customer name
- View accounts for a specific customer

### Transaction Management
- Perform debit operations
- Perform credit operations
- Transfer money between accounts
- View transaction history with pagination
- Filter transactions by account

### Security Features
- Role-based access control (Admin and User roles)
- JWT token-based authentication
- Secure password handling
- Protected API endpoints
- Role-based UI elements

### Audit Trail
- Track user who created each customer
- Track user who created each account
- Track user who performed each operation

## Security Implementation

The application implements a comprehensive security model:

### Backend Security
- **Spring Security 6**: Configures security for the application
- **JWT Authentication**: Generates and validates JWT tokens
- **Role-Based Access Control**: Different endpoints accessible based on user roles
- **Cross-Origin Resource Sharing (CORS)**: Configured to allow frontend access

### Frontend Security
- **JWT Interceptor**: Automatically adds JWT token to API requests
- **Auth Guard**: Protects routes based on user roles
- **Token Storage**: Securely stores JWT tokens
- **Role-Based UI**: Shows/hides UI elements based on user permissions

## Backend Implementation

### Entity Model
The backend is built around the following key entities:

1. **Customer**
   - Properties: id, name, email, createdBy, createdAt
   - Relationships: One-to-Many with BankAccount

2. **BankAccount** (Abstract class)
   - Properties: id, balance, createdAt, status, createdBy
   - Subclasses: CurrentAccount, SavingAccount
   - Relationships: Many-to-One with Customer, One-to-Many with AccountOperation

3. **CurrentAccount** (extends BankAccount)
   - Additional properties: overdraft

4. **SavingAccount** (extends BankAccount)
   - Additional properties: interestRate

5. **AccountOperation**
   - Properties: id, operationDate, amount, description, type, createdBy
   - Relationships: Many-to-One with BankAccount

### Repository Layer
- **CustomerRepository**: Manages customer data operations
- **BankAccountRepository**: Manages account data operations
- **AccountOperationRepository**: Manages transaction data operations

### Service Layer
- **BankAccountService**: Interface defining all banking operations
- **BankAccountServiceImpl**: Implementation of banking operations

### Controller Layer
- **BankAccountRestController**: Exposes REST endpoints for account operations
- **CustomerRestController**: Exposes REST endpoints for customer operations
- **AuthController**: Handles authentication and token generation

### DTO Pattern
The application uses Data Transfer Objects (DTOs) to separate the internal entity model from the API:
- CustomerDTO
- BankAccountDTO
- CurrentBankAccountDTO
- SavingBankAccountDTO
- AccountOperationDTO
- AccountHistoryDTO
- DebitDTO
- CreditDTO
- TransferRequestDTO

### Mapper Pattern
MapStruct is used to map between entities and DTOs:
- CustomerMapper
- BankAccountMapper
- AccountOperationMapper
