package dev.gabriel.wisewallet.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan("dev.gabriel.wisewallet.core")
@ComponentScan("dev.gabriel.wisewallet.currency")
@ComponentScan("dev.gabriel.wisewallet.budget.domain")
@ComponentScan("dev.gabriel.wisewallet.budget.application")
@ComponentScan("dev.gabriel.wisewallet.budget.infrastructure")
@ComponentScan("dev.gabriel.wisewallet.budget.presentation")
@EnableMongoRepositories
@EnableKafka
public class BudgetServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BudgetServiceApp.class, args);
    }
}
