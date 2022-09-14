FROM openjdk:8
EXPOSE 8084
ADD target/authorization-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]