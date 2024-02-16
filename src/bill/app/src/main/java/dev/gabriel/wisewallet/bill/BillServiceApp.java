package dev.gabriel.wisewallet.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan("dev.gabriel.wisewallet.core")
@ComponentScan("dev.gabriel.wisewallet.currency")
@ComponentScan("dev.gabriel.wisewallet.bill.domain")
@ComponentScan("dev.gabriel.wisewallet.bill.application")
@ComponentScan("dev.gabriel.wisewallet.bill.infrastructure")
@ComponentScan("dev.gabriel.wisewallet.bill.presentation")
@EnableMongoRepositories
@EnableKafka
public class BillServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BillServiceApp.class, args);
    }
}
