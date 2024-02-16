package dev.gabriel.wisewallet.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dev.gabriel.wisewallet.user")
public class AuthModule {
    public static void main(String[] args) {
        SpringApplication.run(AuthModule.class, args);
    }
}
