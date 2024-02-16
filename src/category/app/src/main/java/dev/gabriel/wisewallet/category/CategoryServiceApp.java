package dev.gabriel.wisewallet.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(value = "dev.gabriel.wisewallet.core")
@ComponentScan(value = "dev.gabriel.wisewallet.category.domain")
@ComponentScan(value = "dev.gabriel.wisewallet.category.application")
@ComponentScan(value = "dev.gabriel.wisewallet.category.infrastructure")
@ComponentScan(value = "dev.gabriel.wisewallet.category.presentation")
@EnableMongoRepositories
@EnableKafka
public class CategoryServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CategoryServiceApp.class, args);
    }
}
