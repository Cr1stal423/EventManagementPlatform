package com.cr1stal.eventservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EventAlreadyExist extends RuntimeException{
    public EventAlreadyExist(String message){
        super(message);
    }
}
