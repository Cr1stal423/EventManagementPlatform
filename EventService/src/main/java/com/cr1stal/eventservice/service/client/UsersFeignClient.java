package com.cr1stal.eventservice.service.client;

import com.cr1stal.eventservice.DTO.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "users",url = "lb://USERS", fallback = UsersFallback.class)
public interface UsersFeignClient {
    @GetMapping(value = "/api/v1/users/getUserByEmail", consumes = "application/json")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam("email") String email);
}
