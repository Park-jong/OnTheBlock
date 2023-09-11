FROM openjdk:17-jre

WORKDIR /app

COPY build/libs/*SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]