FROM maven:3.9.4-eclipse-temurin-17 AS builder
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk
EXPOSE 8080
COPY --from=build target/geminiHandler-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
