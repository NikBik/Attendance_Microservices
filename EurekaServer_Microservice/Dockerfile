# Stage 1: Build
FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17
COPY --from=build /app/target/eureka-server-microservice-latest.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]