# Build stage
FROM maven:3.6-jdk-11 AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package


# Package stage
FROM openjdk:latest
COPY --from=build /app/target/food-truck-api-0.0.1-SNAPSHOT.jar food-truck-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","food-truck-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
