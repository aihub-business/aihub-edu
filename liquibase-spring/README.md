# Liquibase Course Project

This project contains the materials and examples for the Liquibase course.

## Project Structure

- `liquibase-demo`: Contains a complete working example of Liquibase integration with Spring Boot
- `liquibase-exercises`: Contains exercises for practicing Liquibase concepts

## Prerequisites

- Java 17 or higher
- PostgreSQL database
- Gradle

## Getting Started

1. Clone the repository
2. Configure database connection in `liquibase-demo/src/main/resources/application.yml`
3. Run the demo application:
   ```bash
   ./gradlew :liquibase-demo:bootRun
   ```

## Exercises

The `liquibase-exercises` module contains practical exercises that follow the course material.
Each exercise is in its own directory with instructions and starter files.

## Course Materials

The complete course materials can be found in the docs directory, including:
- Presentation slides
- Example changelog files
- Best practices documentation
