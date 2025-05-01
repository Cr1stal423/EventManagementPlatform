package com.cr1stal423.booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorResponseDto {
    String apiPath;

    HttpStatus errorCode;

    String errorMessage;

    LocalDateTime errorTime;
}
