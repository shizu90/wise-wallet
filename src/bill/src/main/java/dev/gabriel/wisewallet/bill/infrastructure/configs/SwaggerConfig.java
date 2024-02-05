package dev.gabriel.wisewallet.bill.infrastructure.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class SwaggerConfig {
    @Value("${spring.application.version}")
    private String serviceVersion;

    @Value("${spring.application.name}")
    private String serviceName;

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(serviceName)
                        .version(serviceVersion)
                );
    }
}
