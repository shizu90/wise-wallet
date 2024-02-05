package dev.gabriel.wisewallet.recurringbill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan("dev.gabriel.wisewallet.core")
@ComponentScan("dev.gabriel.wisewallet.currency")
@EnableMongoRepositories
@EnableKafka
public class RecurringBillModule {
    public static void main(String[] args) {
        SpringApplication.run(RecurringBillModule.class, args);
    }
}
