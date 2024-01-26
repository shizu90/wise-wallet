package dev.gabriel.wisewallet.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("dev.gabriel.wisewallet.core")
@EnableMongoRepositories
@EnableScheduling
@EnableAsync
public class ReminderModule {
    public static void main(String[] args) {
        SpringApplication.run(ReminderModule.class);
    }
}
