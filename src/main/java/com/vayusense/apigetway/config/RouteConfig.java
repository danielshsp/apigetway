package com.vayusense.apigetway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/login/**")
                        .uri("http://localhost:8761/")
                        .id("monitoredModule"))
                .route(r -> r.path("/auth/login/**")
                        .uri("http://localhost:8080/")
                        .id("apigetway"))
                .route(r -> r.path("/api/v1/backend/**")
                        .uri("http://localhost:8085/")
                        .id("backendModule"))
                .build();
    }
}
