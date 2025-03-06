# Hackathon 2025 API - DB Import/Export Quick Guide 🚀

This guide explains how to use the import/export functionality in the Hackathon 2025 API.

## Release Notes for v1.0.1 🚀

### New Features and Updates:

* **Healthcheck Implementation:** Added a healthcheck for the Neo4j service to ensure it's up and running before the application starts.
* **Java Upgrade:** Upgraded the application to Java 17 (from Java 11).
* **Neo4j Migrations Support:** Integrated `neo4j-migrations` library version 2.13.2 for easier schema management and migrations.
* **V1\_\_Create\_initial\_schema:** Added an initial schema creation with a constraint to ensure data integrity.
* **Refactored Import Data Logic:** Improved the `importData` method by removing redundancy in database operations, optimizing the code, and making it more maintainable.

## Docker Deployment Options

You can choose between using the old version or the new version of the application.

### Old Version:

If you want to continue using the previous version, you can still use the `latest` tag:

* image: qbchak/neo4jdb-app:latest

### New Version:

For the latest features and improvements, you can switch to the new version `v1.0.1`:

* image: qbchak/neo4jdb-app:v1.0.1

**Important:** To use the new version, you must add a `healthcheck` and `depends_on` condition to your `docker-compose.yml` file, as shown in the example below:

## Example `docker-compose.yml`

```yaml
services:
  neo4j:
    image: neo4j:latest
    container_name: neo4j_interview
    healthcheck:
      test: [ "CMD-SHELL", "cypher-shell -u neo4j -p password 'RETURN 1;'" ]
    environment:
      NEO4J_AUTH: neo4j/password
    ports:
      - "7474:7474"
      - "7687:7687"
    volumes:
      - neo4j_data:/data

  app:
    image: qbchak/neo4jdb-app:v1.0.1 
    container_name: neo4j_app
    depends_on:
      neo4j:
        condition: service_healthy
    environment:
      - NEO4J_URL=bolt://neo4j:7687
      - NEO4J_USERNAME=neo4j
      - NEO4J_PASSWORD=password
    ports:
      - "8080:8080"

volumes:
  neo4j_data:
```

✅ Swagger UI for interactive API documentation
http://localhost:8080/api-docs/

✅ Neo4j Browser: for interactive API documentation
http://localhost:7474/ (username: neo4j, password: password)

For ease of use - HTTPie (https://httpie.io/)
✅ Data Import API for inserting structured data into Neo4j
- http POST localhost:8080/api/import < src/main/resources/json/import.json

✅ Data Export API for retrieving stored data
- http GET localhost:8080/api/export

✅ Docker deployment for easy distribution !!!
- add docker-compose.yaml (see attached)
- run % docker-compose up
- add import.json into src/main/resources/json/import.json (see attached)
- enjoy

##### JSON Example:
- (src/main/resources/json/import.json)
