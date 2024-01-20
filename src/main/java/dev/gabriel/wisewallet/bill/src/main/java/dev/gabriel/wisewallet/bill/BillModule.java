package dev.gabriel.wisewallet.bill;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EntityScan
@EnableMongoRepositories
@EnableScheduling
@EnableAsync
public class BillModule {
    public static void main(String[] args) {

    }
}
