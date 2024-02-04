package dev.gabriel.wisewallet.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(value = "dev.gabriel.wisewallet.core")
@EnableMongoRepositories
@EnableKafka
public class CategoryModule {
    public static void main(String[] args) {
        SpringApplication.run(CategoryModule.class, args);
    }
}
