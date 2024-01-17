package dev.gabriel.wisewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("dev.gabriel.wisewallet.core")
@ComponentScan
@EntityScan
@EnableMongoRepositories
@EnableScheduling
@EnableAsync
public class WalletModule {
    public static void main(String[] args) {
        SpringApplication.run(WalletModule.class, args);
    }
}
