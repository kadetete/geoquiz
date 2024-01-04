FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /backend
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /backend
COPY --from=builder /backend/target/*.jar /backend/app.jar
EXPOSE 8080