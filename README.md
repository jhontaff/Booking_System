# Project Overview
This project implements an authentication and authorization system using JSON Web Tokens (JWT) in a Spring Boot application. It includes centralized exception handling, role-based access control, and uses DTOs (Data Transfer Objects) to structure the flow of data between the client and server.

# Features
- JWT Authentication: Secure login system with JWT to manage user sessions.
- Role-Based Authorization: Control access to resources based on user roles.
- Centralized Exception Handling: Consistent error handling using @RestControllerAdvice.
- DTO Usage: Implemented DTOs for safe and efficient data transfer between layers.

#Technologies Used
1. Spring Boot: Core framework for building the application.
2. Spring Security: Provides authentication and authorization mechanisms.
3. JWT: Used for generating and validating tokens.
4. Hibernate/JPA: For database interaction.
5. MySQL: Database for storing user data.
6. Maven: Dependency management and build automation.

# Setup Instructions
Clone the repository.
Set up the database (MySQL) and configure the connection in application.properties.
Build the project using Maven.
Run the application and access the API endpoints as needed.
