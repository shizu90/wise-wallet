package dev.gabriel.wisewallet.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("dev.gabriel.wisewallet.core")
@ComponentScan("dev.gabriel.wisewallet.currency")
@EnableMongoRepositories
public class BudgetModule {
    public static void main(String[] args) {
        SpringApplication.run(BudgetModule.class, args);
    }
}
