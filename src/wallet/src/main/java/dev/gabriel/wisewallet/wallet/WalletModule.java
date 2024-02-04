package dev.gabriel.wisewallet.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(value = "dev.gabriel.wisewallet.core")
@ComponentScan(value = "dev.gabriel.wisewallet.currency")
@EnableMongoRepositories
@EnableKafka
public class WalletModule {
    public static void main(String[] args) {
        SpringApplication.run(WalletModule.class, args);
    }
}
