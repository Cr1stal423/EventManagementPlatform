package com.cr1stal423.booking.service.client;

import com.cr1stal423.booking.DTO.EventDto;
import com.cr1stal423.booking.DTO.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EventClientFallback implements EventClient{

    @Override
    public ResponseEntity<EventDto> getEventById(Long eventId) {
        return null;
    }
}
