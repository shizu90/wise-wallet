FROM openjdk:21

EXPOSE 8080

ADD target/gateway.jar gateway.jar

ENTRYPOINT ["java", "-jar", "/gateway.jar"]