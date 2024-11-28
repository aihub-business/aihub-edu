# PostgreSQL Docker Setup for Liquibase Testing

This setup provides a PostgreSQL database and pgAdmin for database management.

## Configuration

- PostgreSQL Database:
    - Host: localhost
    - Port: 5432
    - Database: liquibase_demo
    - Username: postgres
    - Password: postgres

- pgAdmin:
    - URL: http://localhost:5050
    - Email: admin@admin.com
    - Password: admin

## Getting Started

1. Start the containers:
```bash
docker-compose up -d
```

2. Stop the containers:
```bash
docker-compose down
```

3. Delete everything including volumes:
```bash
docker-compose down -v
```

## Connecting to Database

### Using psql CLI:
```bash
docker exec -it liquibase_postgres psql -U postgres -d liquibase_demo
```

### Using pgAdmin:
1. Open http://localhost:5050 in your browser
2. Log in using admin@admin.com / admin
3. Add New Server:
    - Host: postgres
    - Port: 5432
    - Database: liquibase_demo
    - Username: postgres
    - Password: postgres

## Database Commands

Check database status:
```bash
docker exec liquibase_postgres pg_isready
```

View logs:
```bash
docker logs liquibase_postgres
```

Connect using external tools:
- Host: localhost
- Port: 5432
- Database: liquibase_demo
- Username: postgres
- Password: postgres

## Common Issues

1. Port conflict:
    - If port 5432 is already in use, modify the port mapping in docker-compose.yml
    - Example: change "5432:5432" to "5433:5432"

2. Permission issues:
    - Make sure your user has permissions to create Docker volumes
    - Run `sudo chown -R 5050:5050 pgadmin_data` if pgAdmin has permission issues

3. Connection refused:
    - Wait a few seconds after starting containers for PostgreSQL to initialize
    - Check logs using `docker logs liquibase_postgres`