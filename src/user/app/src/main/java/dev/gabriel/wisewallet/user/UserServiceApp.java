package dev.gabriel.wisewallet.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(value = "dev.gabriel.wisewallet.core")
@ComponentScan(value = "dev.gabriel.wisewallet.user.domain")
@ComponentScan(value = "dev.gabriel.wisewallet.user.application")
@ComponentScan(value = "dev.gabriel.wisewallet.user.infrastructure")
@ComponentScan(value = "dev.gabriel.wisewallet.user.presentation")
@EnableMongoRepositories
@EnableKafka
public class UserServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApp.class, args);
    }
}
