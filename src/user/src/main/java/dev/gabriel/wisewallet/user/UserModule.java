package dev.gabriel.wisewallet.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(value = "dev.gabriel.wisewallet.core")
@EnableMongoRepositories
@EnableKafka
public class UserModule {
    public static void main(String[] args) {
        SpringApplication.run(UserModule.class, args);
    }
}
