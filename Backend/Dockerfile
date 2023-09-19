FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "-Dspring-boot.run.arguments=--DB_USER=${DB_USER}, --DB_PW=${DB_PW}, --BASE_URL=${BASE_URL}, --FRONT_PORT=${FRONT_PORT}"]