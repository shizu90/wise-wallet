package dev.gabriel.wisewallet.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(value = "dev.gabriel.wisewallet.core")
@ComponentScan
@EntityScan
@EnableMongoRepositories
@EnableScheduling
@EnableAsync
public class CategoryModule {
    public static void main(String[] args) {
        SpringApplication.run(CategoryModule.class, args);
    }
}
