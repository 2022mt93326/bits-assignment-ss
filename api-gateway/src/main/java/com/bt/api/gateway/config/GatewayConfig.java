package com.bt.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter filter;

    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .route("product-service", r -> r.path("/product-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://product-service"))
                .route("quotation-service", r -> r.path("/quotation-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://quotation-service"))
                .route("rule-engine", r -> r.path("/rule-engine/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://rule-engine"))
                .build();
    }

}