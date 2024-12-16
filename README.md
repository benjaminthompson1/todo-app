# Spring Boot Todo Application

A simple Todo application built with Spring Boot, Thymeleaf, and containerized using Docker/Podman.

## Project Structure
```
todo-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── todo/
│       │               ├── TodoApplication.java        # Main Spring Boot Application
│       │               ├── controller/
│       │               │   └── TodoController.java     # MVC Controller
│       │               ├── model/
│       │               │   └── Todo.java              # Todo Entity
│       │               └── service/
│       │                   └── TodoService.java       # Business Logic Layer
│       └── resources/
│           └── templates/
│               └── todos.html                         # Thymeleaf Template
├── pom.xml                                           # Maven Dependencies
├── Dockerfile                                        # Multi-stage Container Build
└── docker-compose.yml                                # Container Orchestration
```

## Technology Stack
- Java 17
- Spring Boot 2.7.0
- Thymeleaf
- Maven
- Docker/Podman
- Spring MVC

## Setup & Installation

### Prerequisites
- Java Development Kit (JDK) 17
- Maven 3.6+
- Docker/Podman

### Local Development
```bash
# Clone repository
git clone <repository-url>

# Build application
mvn clean package

# Run locally
java -jar target/todo-app-0.0.1-SNAPSHOT.jar
```

### Container Deployment

Using Docker:
```bash
docker-compose up --build
```

Using Podman:
```bash
# Build container
podman build -t todo-app .

# Run container
podman run -d --name todo-app -p 3001:8080 todo-app
```

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/` | Display todos list |
| POST | `/add` | Create new todo |
| POST | `/toggle/{id}` | Toggle todo completion |
| POST | `/delete/{id}` | Delete todo |

## Configuration

Key configuration files:
- `application.properties`: Spring Boot configuration
- `pom.xml`: Project dependencies
- `Dockerfile`: Container build instructions
- `docker-compose.yml`: Container service definitions


