#FROM maven:3.9.6-eclipse-temurin-17 AS build
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
#WORKDIR /app
COPY . .

RUN apt-get install maven -y
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-slim
EXPOSE 8080
#WORKDIR /app
COPY --from=build /app/target/relatorio-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

