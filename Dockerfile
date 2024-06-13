# Dockerfile
FROM openjdk:17-oracle
WORKDIR /app
COPY target/ecoGrad-0.0.1-SNAPSHOT.jar /app/ecoGrad.jar
EXPOSE 8080
CMD ["java", "-jar", "ecoGrad.jar"]