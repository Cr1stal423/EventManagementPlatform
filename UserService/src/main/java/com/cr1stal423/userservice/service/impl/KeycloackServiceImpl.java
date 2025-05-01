package com.cr1stal423.userservice.service.impl;

import com.cr1stal423.userservice.service.IKeycloackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloackServiceImpl implements IKeycloackService {
    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private String keycloakUrl;

    private String adminToken;

    public KeycloackServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String createUser(String username, String email, String password) {

        return "";
    }
}
