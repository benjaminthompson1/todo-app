# Spring Boot Todo Application

A simple Todo application built with Spring Boot, Thymeleaf, and containerized using Docker/Podman, with Db2 z/OS backend storage.

## Project Overview

This application provides a basic todo list management system with a web interface, built using Spring Boot and Thymeleaf templating engine. It supports containerized deployment using either Docker or Podman and stores data in Db2 for z/OS.

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
│           ├── Db2/
│           │   └── schema.ddl                         # Db2 z/OS DDL Scripts
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
- Db2 for z/OS

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17
- Maven 3.6+
- Docker or Podman (for containerized deployment)
- Git (optional, for cloning)
- curl (for downloading source archive)
- Access to Db2 for z/OS instance

### Initial Setup

You can set up the project either by cloning the repository or downloading the source archive.

#### Option 1: Using Git
```bash
# Clone repository
git clone https://github.com/benjaminthompson1/todo-app.git
cd todo-app
```

#### Option 2: Using Source Archive zCX
```bash
# Create and navigate to project directory
mkdir todo-app
cd todo-app

# Download the source archive
curl -L https://github.com/benjaminthompson1/todo-app/archive/refs/heads/main.tar.gz -o todo-app.tar.gz

# Extract the archive
tar -xzf todo-app.tar.gz

# Navigate to the extracted project directory
cd todo-app-main
```

### Database Setup

1. Navigate to the DDL script location:
```bash
cd src/main/resources/Db2
```

2. Execute the DDL script in Db2 for z/OS:
```bash
db2 -tvf schema.ddl
```

### Container Deployment

#### Using Docker
```bash
# Build and run using docker-compose
docker-compose up --build

# Alternative: Manual build and run
docker build -t todo-app .
docker run -d --name todo-app -p 3001:8080 todo-app
```

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/` | Display todos list |
| POST | `/add` | Create new todo |
| POST | `/toggle/{id}` | Toggle todo completion |
| POST | `/delete/{id}` | Delete todo |

## Configuration

### Key Configuration Files
- `application.properties`: Spring Boot configuration
- `pom.xml`: Project dependencies and build configuration
- `Dockerfile`: Container build instructions
- `docker-compose.yml`: Container service definitions
- `src/main/resources/Db2/schema.ddl`: Db2 database schema

### Application Access
- Local development: http://localhost:8080
- Container deployment: http://localhost:3001

### Verification Steps
1. After setup, verify the project structure matches the provided directory layout
2. Ensure all configuration files are present
3. Confirm successful Maven build with no errors
4. Verify successful DDL execution in Db2
5. Verify application startup and accessibility via web browser