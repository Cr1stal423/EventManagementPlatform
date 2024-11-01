package com.cr1stal.eventservice.service;

import com.cr1stal.eventservice.DTO.EventDetailsDto;
import com.cr1stal.eventservice.DTO.EventDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IEventService {
    void createEvent(EventDetailsDto eventDetailsDto);

    EventDto getEventById(Long eventId);

    boolean updateEvent(EventDto eventDto, Long eventId);

    boolean deleteEvent(Long eventId);

    Set<EventDto> getAllEventsByOrganizer(String organizerEmail);

    Set<EventDto> getAllEventsByCategory(String categoryName);

    List<EventDto> getAllSoonerEvents(LocalDate startDate, LocalDate endDate);

}
