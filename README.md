# Spring Webhook Solver

A Spring Boot application for webhook management and solving, built with Maven.

## Project Details

- **Language**: Java 17
- **Build Tool**: Maven 3.6+
- **Framework**: Spring Boot 3.2.0
- **Database**: H2 (In-Memory)

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/webhook/
│   │       ├── SpringWebhookSolverApplication.java    # Main application class
│   │       └── controller/
│   │           └── HelloController.java                # Sample REST controller
│   └── resources/
│       └── application.properties                       # Application configuration
└── test/
    └── java/
        └── com/webhook/
            └── SpringWebhookSolverApplicationTests.java # Test class
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Building the Project

```bash
mvn clean package
```

### Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Available Endpoints

- `GET /api/hello` - Returns a greeting message
- `GET /api/health` - Returns the application health status
- `GET /h2-console` - H2 Database Console (Development only)

## Dependencies

- **spring-boot-starter-web**: REST API support
- **spring-boot-starter-data-jpa**: Database operations
- **h2**: In-memory database
- **lombok**: Reduce boilerplate code
- **spring-boot-starter-test**: Testing support

## Configuration

Application properties can be modified in `src/main/resources/application.properties`:

- `spring.jpa.hibernate.ddl-auto`: Database schema generation (currently set to `update`)
- `spring.h2.console.enabled`: Enable H2 console access
- Logging levels can be configured per package

## Next Steps

1. Add webhook event models and repositories
2. Implement webhook event handling logic
3. Create service layer for business logic
4. Add comprehensive test cases
5. Configure production database (e.g., PostgreSQL, MySQL)

## License

This project is open source and available under the MIT License.
