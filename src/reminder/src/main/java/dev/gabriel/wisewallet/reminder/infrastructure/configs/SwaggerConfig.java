package dev.gabriel.wisewallet.reminder.infrastructure.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("WiseWallet: Reminder service")
                        .version("1.0.0")
                );
    }
}
