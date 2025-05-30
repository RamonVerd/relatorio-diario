# Etapa 1: Build
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Faz o build do projeto (gera o JAR)
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Comando para rodar
ENTRYPOINT ["java", "-jar", "app.jar"]


