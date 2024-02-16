package dev.gabriel.wisewallet.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("dev.gabriel.wisewallet.core")
@ComponentScan("dev.gabriel.wisewallet.reminder.domain")
@ComponentScan("dev.gabriel.wisewallet.reminder.application")
@ComponentScan("dev.gabriel.wisewallet.reminder.infrastructure")
@ComponentScan("dev.gabriel.wisewallet.reminder.presentation")
@EnableMongoRepositories
@EnableScheduling
@EnableAsync
@EnableKafka
public class ReminderServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ReminderServiceApp.class, args);
    }
}
