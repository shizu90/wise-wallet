package dev.gabriel.wisewallet.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(value = "dev.gabriel.wisewallet.core")
@ComponentScan(value = "dev.gabriel.wisewallet.currency")
@ComponentScan(value = "dev.gabriel.wisewallet.domain")
@ComponentScan(value = "dev.gabriel.wisewallet.application")
@ComponentScan(value = "dev.gabriel.wisewallet.infrastructure")
@ComponentScan(value = "dev.gabriel.wisewallet.presentation")
@EnableMongoRepositories
@EnableKafka
public class WalletServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApp.class, args);
    }
}
