FROM openjdk:21

EXPOSE 8087

ADD target/budget.jar budget.jar

ENTRYPOINT ["java", "-jar", "/budget.jar"]