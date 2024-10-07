# Booking_System

## English

## Description
This project is a JWT (JSON Web Tokens) based authentication API developed with Spring. It provides functionalities for managing bookings, users, and additional resources, including two-factor authentication (2FA) and role validation using JWT.

## Technologies Used
- **Java**: Main programming language.
- **Spring**: Framework for Java application development.
    - **Spring Boot**: For application configuration and startup.
    - **Spring Security**: For authentication and authorization.
    - **Spring Data JPA**: For database interaction.
- **Maven**: Dependency management and project build tool.
- **SQL**: Relational database for data storage.
- **JUnit**: Framework for unit testing.
- **Mockito**: Framework for creating mocks in unit tests.
- **JWT**: JSON Web Tokens for authentication.

## Features
- **JWT Authentication**: Secure authentication using JSON Web Tokens.
- **Two-Factor Authentication (2FA)**: Enhances security through two-step verification.
- **User Management**: User registration, login, and management.
- **Booking Management**: Create, retrieve, and manage bookings.
- **Role Validation**: Role-based access control using JWT.
- **Custom Exception Handling**: Management of application-specific errors.
- **DTO Usage**: Structured data transfer between application layers.

## Project Structure
- `src/main/java`: Contains the application source code.
- `src/test/java`: Contains unit tests.
- `src/main/resources`: Contains configuration files and static resources.

## Environment Setup
1. **Clone the repository**:
    ```bash
    git clone https://github.com/jhontaff/Booking_System.git
    cd Booking_System
    ```

2. **Configure the database**:
    - Ensure you have a configured SQL database and update the `application.properties` file with your database credentials.

3. **Build the project**:
    ```bash
    mvn clean install
    ```

4. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

## Running Tests
To run the unit tests, use the following command:
```bash
mvn test
```


## Español

## Descripción
Este proyecto es una API de autenticación basada en JWT (JSON Web Tokens) desarrollada con Spring. Proporciona funcionalidades para la gestión de reservas, usuarios y recursos adicionales, incluyendo autenticación de dos factores (2FA) y validación de roles mediante JWT.

## Tecnologías Utilizadas
- **Java**: Lenguaje de programación principal.
- **Spring**: Framework para el desarrollo de aplicaciones Java.
    - **Spring Boot**: Para la configuración y el arranque de la aplicación.
    - **Spring Security**: Para la autenticación y autorización.
    - **Spring Data JPA**: Para la interacción con la base de datos.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **SQL**: Base de datos relacional para el almacenamiento de datos.
- **JUnit**: Framework para pruebas unitarias.
- **Mockito**: Framework para la creación de mocks en pruebas unitarias.
- **JWT**: JSON Web Tokens para la autenticación.


## Funcionalidades
- **Autenticación con JWT**: Autenticación segura utilizando JSON Web Tokens.
- **Autenticación de Dos Factores (2FA)**: Mejora la seguridad mediante la verificación en dos pasos.
- **Gestión de Usuarios**: Registro, inicio de sesión y gestión de usuarios.
- **Gestión de Reservas**: Crear, obtener y gestionar reservas.
- **Validación de Roles**: Control de acceso basado en roles mediante JWT.
- **Manejo de Excepciones Personalizadas**: Gestión de errores específicos de la aplicación.
- **Uso de DTOs**: Transferencia de datos estructurada entre capas de la aplicación.

## Estructura del Proyecto
- `src/main/java`: Contiene el código fuente de la aplicación.
- `src/test/java`: Contiene las pruebas unitarias.
- `src/main/resources`: Contiene los archivos de configuración y recursos estáticos.

## Configuración del Entorno
1. **Clonar el repositorio**:
    ```bash
    git clone https://github.com/jhontaff/Booking_System.git
    cd Booking_System
    ```

2. **Configurar la base de datos**:
    - Asegúrate de tener una base de datos SQL configurada y actualiza el archivo `application.properties` con las credenciales de tu base de datos.

3. **Construir el proyecto**:
    ```bash
    mvn clean install
    ```

4. **Ejecutar la aplicación**:
    ```bash
    mvn spring-boot:run
    ```

## Ejecución de Pruebas
Para ejecutar las pruebas unitarias, utiliza el siguiente comando:
```bash
mvn test
```
