Hackathon 2025 API - DB Import/Export Quick Guide 🚀

This guide explains how to use import/export functionality in the Hackathon 2025 API.

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
