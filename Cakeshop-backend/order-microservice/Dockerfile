FROM openjdk:17
EXPOSE 9095
ADD target/cook-microservice-0.0.1-SNAPSHOT.jar cook-microservice.jar
ENTRYPOINT ["java","-jar","/cook-microservice.jar"]