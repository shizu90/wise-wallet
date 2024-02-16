FROM openjdk:21

EXPOSE 8083

ADD target/wallet.jar wallet.jar

ENTRYPOINT ["java", "-jar", "/wallet.jar"]