FROM openjdk:21

EXPOSE 8086

ADD target/reminder.jar reminder.jar

ENTRYPOINT ["java", "-jar", "/reminder.jar"]