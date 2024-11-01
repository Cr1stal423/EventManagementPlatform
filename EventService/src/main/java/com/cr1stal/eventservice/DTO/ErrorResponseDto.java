package com.cr1stal.eventservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String path;

    private HttpStatus statusCode;

    private String message;

    private LocalDateTime errorTime;
}
