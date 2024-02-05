FROM openjdk:21

EXPOSE 8081

ADD target/dicovery.jar discovery.jar

ENTRYPOINT ["java", "-jar", "/discovery.jar"]