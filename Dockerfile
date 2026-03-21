# Этап 1: Сборка приложения
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: Запуск приложения
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Убираем EXPOSE 8080, Render сам управляет портом
# EXPOSE 8080

# Команда запуска с использованием PORT из Environment Variables
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]