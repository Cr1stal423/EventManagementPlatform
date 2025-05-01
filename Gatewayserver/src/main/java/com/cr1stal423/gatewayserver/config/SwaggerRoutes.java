package com.cr1stal423.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerRoutes {
    @Bean
    public RouteLocator swaggerApiRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("user_service_swagger", r -> r.path("/aggregate/users-service/v3/api-docs")
                        .filters(f -> f.rewritePath("/aggregate/users-service/v3/api-docs", "/v3/api-docs"))
                        .uri("lb://USERS"))
                .route("event_service_swagger", r -> r.path("/aggregate/event-service/v3/api-docs")
                        .filters(f -> f.rewritePath("/aggregate/event-service/v3/api-docs","/v3/api-docs"))
                        .uri("lb://EVENTS"))
                .build();
    }
}
