FROM openjdk:21

EXPOSE 8082

ADD target/user.jar user.jar

ENTRYPOINT ["java", "-jar", "/user.jar"]