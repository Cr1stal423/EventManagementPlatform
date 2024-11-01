package com.cr1stal.eventservice.mapper;

import com.cr1stal.eventservice.DTO.CategoryDto;
import com.cr1stal.eventservice.DTO.EventDetailsDto;
import com.cr1stal.eventservice.DTO.EventDto;
import com.cr1stal.eventservice.DTO.OrganizerDto;
import com.cr1stal.eventservice.model.Event;
import lombok.Data;

@Data
public class EventMapper {
    public static EventDto mapToEventDto(Event event, EventDto eventDto){
        eventDto.setEventDate(event.getEventDate());
        eventDto.setTitle(event.getTitle());
        eventDto.setLocation(event.getLocation());
        eventDto.setDuration(event.getDuration());
        eventDto.setAvailableSeats(event.getAvailableSeats());
        eventDto.setDescription(event.getDescription());

        return eventDto;
    }

    public static Event mapToEvent(EventDto eventDto, Event event){
        event.setEventDate(eventDto.getEventDate());
        event.setTitle(eventDto.getTitle());
        event.setLocation(eventDto.getLocation());
        event.setDuration(eventDto.getDuration());
        event.setAvailableSeats(eventDto.getAvailableSeats());
        event.setDescription(eventDto.getDescription());

        return event;
    }
    public static EventDetailsDto mapToCustomerDetailsDto(EventDto eventDto, OrganizerDto organizerDto, CategoryDto categoryDto){
        EventDetailsDto eventDetailsDto = new EventDetailsDto();
        eventDetailsDto.setEventDto(eventDto);
        eventDetailsDto.setOrganizerDto(organizerDto);
        eventDetailsDto.setCategoryDto(categoryDto);
        return eventDetailsDto;
    }
}
