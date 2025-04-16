FROM maven:3.9.9-amazoncorretto-23 AS builder
WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jdk
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
