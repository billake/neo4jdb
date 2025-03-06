FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY target/scala-2.13/neo4jdb-app.jar /app/neo4jdb-app.jar
CMD ["java", "-jar", "neo4jdb-app.jar"]