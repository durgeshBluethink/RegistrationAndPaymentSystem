src
│
├── main
│   ├── java
│   │   └── com
│   │       └── RegistrationAndPaymentSystem
│   │           ├── config
│   │           │   └── SecurityConfig.java
│   │           ├── controller
│   │           │   ├── AuthController.java
│   │           │   └── UserController.java
│   │           ├── dto
│   │           │   └── UserDTO.java
│   │           ├── entity
│   │           │   └── User.java
│   │           ├── exception
│   │           │   ├── GlobalExceptionHandler.java
│   │           │   ├── ResourceNotFoundException.java
│   │           │   ├── ValidationException.java
│   │           │   ├── UnauthorizedAccessException.java
│   │           │   └── PaymentProcessingException.java
│   │           ├── repository
│   │           │   └── UserRepository.java
│   │           ├── security
│   │           │   ├── JwtAuthenticationFilter.java
│   │           │   ├── JwtUtil.java
│   │           │   └── CustomUserDetailsService.java
│   │           ├── service
│   │           │   ├── UserService.java
│   │           │   └── UserServiceImpl.java
│   │           └── RegistrationAndPaymentSystemApplication.java
│   └── resources
│       ├── application.properties
│       └── static
└── test
Folder Structure
src/main/java/com/RegistrationAndPaymentSystem/
config/: JWT and security configurations
controller/: Contains controllers for user registration, payment, and admin actions
dto/: Contains DTO classes (data transfer objects)
entity/: Contains entity classes for the database
exception/: Contains custom exceptions and exception handling classes
repository/: Contains repository interfaces for database operations
service/: Contains service interfaces for business logic
service/impl/: Contains service implementations
util/: Utility classes (e.g., JWT token generation)
