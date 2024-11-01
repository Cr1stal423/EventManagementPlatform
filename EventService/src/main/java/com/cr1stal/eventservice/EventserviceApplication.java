package com.cr1stal.eventservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableDiscoveryClient
@EnableFeignClients
public class EventserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventserviceApplication.class, args);
	}

}
