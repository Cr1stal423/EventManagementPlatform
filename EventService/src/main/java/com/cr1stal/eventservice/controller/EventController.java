package com.cr1stal.eventservice.controller;

import com.cr1stal.eventservice.DTO.EventDetailsDto;
import com.cr1stal.eventservice.DTO.EventDto;
import com.cr1stal.eventservice.DTO.ResponseDto;
import com.cr1stal.eventservice.constants.EventConstants;
import com.cr1stal.eventservice.service.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/v1/events", produces = "application/json")
@AllArgsConstructor
public class EventController {
    private IEventService eventService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEvent(@RequestBody EventDetailsDto eventDetailsDto) {
        eventService.createEvent(eventDetailsDto);
        return ResponseEntity.ok(new ResponseDto(EventConstants.STATUS_201, EventConstants.MESSAGE_201));
    }

    @GetMapping("/getById/{eventId}")
    public ResponseEntity<EventDto> getEventById(@PathVariable("eventId") Long eventId) {
        EventDto eventDto = eventService.getEventById(eventId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDto);

    }
    @GetMapping("/getAllOrganizerEvents/{organizerEmail}")
    public ResponseEntity<Set<EventDto>> getAllOrganizerEvents(@PathVariable("organizerEmail") String organizerEmail) {
        Set<EventDto> eventDtos = eventService.getAllEventsByOrganizer(organizerEmail);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDtos);
    }

    @GetMapping("/getAllEventsByCategoty/{categoryName}")
    public ResponseEntity<Set<EventDto>> getAllEventsByCategoty(@PathVariable("categoryName") String categoryName) {
        Set<EventDto> eventDtos = eventService.getAllEventsByCategory(categoryName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDtos);
    }

    @GetMapping("/getAllSoonerEvents")
    public ResponseEntity<List<EventDto>> getAllSoonerEvents(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        List<EventDto> eventDtos = eventService.getAllSoonerEvents(startDate, endDate);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDtos);
    }

    @PutMapping("/update/{eventId}")
    public ResponseEntity<ResponseDto> updateEvent(@PathVariable("eventId") Long eventId, @RequestBody EventDto eventDto) {
        boolean isUpdated = eventService.updateEvent(eventDto, eventId);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(EventConstants.STATUS_200, EventConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(EventConstants.STATUS_417, EventConstants.MESSAGE_417_UPDDATE));
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<ResponseDto> deleteEvent(@PathVariable("eventId") Long eventId) {
        boolean isDeleted = eventService.deleteEvent(eventId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(EventConstants.STATUS_200, EventConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(EventConstants.STATUS_417, EventConstants.MESSAGE_417_DELETE));
    }

}
