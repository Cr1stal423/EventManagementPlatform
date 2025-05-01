package com.cr1stal423.booking.service.client;

import com.cr1stal423.booking.DTO.EventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-service", url = "lb://EVENTS", fallback = EventClientFallback.class)
public interface EventClient {
    @GetMapping("/getById/{eventId}")
    public ResponseEntity<EventDto> getEventById(@PathVariable("eventId") Long eventId);

}
