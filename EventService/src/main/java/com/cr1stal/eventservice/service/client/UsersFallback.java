package com.cr1stal.eventservice.service.client;

import com.cr1stal.eventservice.DTO.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UsersFallback implements UsersFeignClient {
    @Override
    public ResponseEntity<UserDto> getUserByEmail(String email) {
        return null;
    }
}
