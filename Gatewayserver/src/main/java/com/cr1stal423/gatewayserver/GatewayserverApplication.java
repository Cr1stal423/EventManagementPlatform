package com.cr1stal423.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication

public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocatorConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/eventsSystem/userService/**")
						.filters(f -> f.rewritePath("/eventsSystem/userService/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Respomse-Time", LocalDateTime.now().toString()))
						.uri("lb://USERSERVICE")
				).build();
	}
}
