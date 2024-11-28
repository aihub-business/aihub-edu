# Pure Liquibase with Gradle Example

This project demonstrates how to use Liquibase with Gradle without Spring Boot dependencies.

## Prerequisites

- Java 17 or higher
- PostgreSQL database
- Gradle

## Project Structure

```
liquibase-standalone/
├── build.gradle           # Gradle build configuration
├── gradle.properties     # Gradle properties
├── settings.gradle       # Gradle settings
└── src/
    └── main/
        ├── java/         # Java source files
        └── resources/    # Resource files including Liquibase changelogs
```

## Database Configuration

Configure your database connection in:
1. `build.gradle` under the `liquibase` block
2. `src/main/resources/config/liquibase.properties`

## Running Migrations

Using Gradle:
```bash
# Update database to latest version
./gradlew update

# Show pending changesets
./gradlew status

# Generate SQL update script
./gradlew updateSQL

# Rollback last changeset
./gradlew rollbackCount -Pliquibase.rollbackCount=1

# Rollback to specific tag
./gradlew rollback -PliquibaseCommandValue=1.0.0
```

Using Java:
```bash
# Compile and run the Java application
./gradlew build
java -jar build/libs/liquibase-standalone-1.0.0-SNAPSHOT.jar
```

## Development

1. Create new changeset files in `src/main/resources/db/changelog/changes/`
2. Add them to `db.changelog-master.xml`
3. Run the migration

## Best Practices

1. Always include rollback commands
2. Use meaningful changeset IDs
3. Keep changesets atomic
4. Test migrations and rollbacks
5. Use source control for changelog files
