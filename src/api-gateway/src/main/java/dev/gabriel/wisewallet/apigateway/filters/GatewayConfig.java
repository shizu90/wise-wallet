package dev.gabriel.wisewallet.apigateway.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GatewayConfig {
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service",
                        r -> r.path("/api/users/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://user-service")
                )
                .route("wallet-service",
                        r -> r.path("/api/wallets/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://wallet-service")
                )
                .route("bill-service",
                        r -> r.path("/api/bills/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://bill-service")
                )
                .route("category-service",
                        r -> r.path("/api/categories/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://category-service")
                )
                .route("reminder-service",
                        r -> r.path("/api/reminders/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://reminder-service")
                )
                .route("budget-service",
                        r -> r.path("/api/budgets/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://budget-service")
                )
                .route("recurring-bill-service",
                        r -> r.path("/api/recurringbills/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://recurring-bill-service")
                )
                .route("auth-service",
                        r -> r.path("/api/auth/**")
                                .filters(f -> f.filter(authenticationFilter))
                                .uri("lb://auth-service")
                )
                .build();
    }
}
