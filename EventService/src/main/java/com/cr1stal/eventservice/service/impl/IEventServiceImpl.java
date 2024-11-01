package com.cr1stal.eventservice.service.impl;

import com.cr1stal.eventservice.DTO.EventDetailsDto;
import com.cr1stal.eventservice.DTO.EventDto;
import com.cr1stal.eventservice.DTO.UserDto;
import com.cr1stal.eventservice.exception.ResourceNotFoundException;
import com.cr1stal.eventservice.mapper.EventMapper;
import com.cr1stal.eventservice.model.Category;
import com.cr1stal.eventservice.model.Event;
import com.cr1stal.eventservice.model.Organizer;
import com.cr1stal.eventservice.repository.CategoryRepository;
import com.cr1stal.eventservice.repository.EventRepository;
import com.cr1stal.eventservice.repository.OrganizerRepository;
import com.cr1stal.eventservice.service.IEventService;
import com.cr1stal.eventservice.service.client.UsersFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IEventServiceImpl implements IEventService {
    private EventRepository eventRepository;
    private OrganizerRepository organizerRepository;
    private CategoryRepository categoryRepository;
    private UsersFeignClient usersFeignClient;
    @Override
    public void createEvent(EventDetailsDto eventDetailsDto) {
        Event event = EventMapper.mapToEvent(eventDetailsDto.getEventDto(), new Event());
        Category category = categoryRepository.findByCategoryName(eventDetailsDto.getCategoryDto().getCategoryName()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "name", eventDetailsDto.getCategoryDto().getCategoryName())
        );
       ResponseEntity<UserDto> userDtoResponseEntity = usersFeignClient.getUserByEmail(eventDetailsDto.getOrganizerDto().getEmail());
       UserDto userDto = userDtoResponseEntity.getBody();
       Organizer organizer = new Organizer();
        organizer.setFirstName(userDto.getFirstName());
        organizer.setLastName(userDto.getLastName());
        organizer.setEmail(userDto.getEmail());

        organizer.addEvent(event);
        event.setOrganizer(organizer);
        event.setCategory(category);
        organizerRepository.save(organizer);
        eventRepository.save(event);

    }

    @Override
    public EventDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id",eventId.toString())
        );
        EventDto eventDto = EventMapper.mapToEventDto(event, new EventDto());
        return eventDto;
    }

    @Override
    public boolean updateEvent(EventDto eventDto, Long eventId) {
        boolean isUpdated = false;
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", eventId.toString())
        );
        EventMapper.mapToEvent(eventDto, event);
        eventRepository.save(event);
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteEvent(Long eventId) {
        boolean isDeleted = false;
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", eventId.toString())
        );
        eventRepository.delete(event);
        isDeleted = true;
        return isDeleted;
    }

    @Override
    public Set<EventDto> getAllEventsByOrganizer(String organizerEmail) {
        Organizer organizer = organizerRepository.findByEmail(organizerEmail).orElseThrow(
                () -> new ResourceNotFoundException("Organizer", "email", organizerEmail)
        );
        Set<Event> events = organizerRepository.getAllEventsByOrganizer(organizer.getEmail());
        if (events != null && !events.isEmpty()) {
            Set<EventDto> eventDtos = events.stream().map(event ->
                    EventMapper.mapToEventDto(event,new EventDto())).collect(Collectors.toSet());
            return  eventDtos;
        }
        return new HashSet<>();
    }

    @Override
    public Set<EventDto> getAllEventsByCategory(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName).orElseThrow(
                () -> new ResourceNotFoundException("Category", "name", categoryName)
        );
        Set<Event> events = eventRepository.getAllByCategoryCategoryNameEquals(categoryName);
        if (events != null && !events.isEmpty()) {
            Set<EventDto> eventDtoSet = events.stream().map(event ->
                    EventMapper.mapToEventDto(event, new EventDto())).collect(Collectors.toSet());
            return eventDtoSet;
        }
        return new HashSet<>();
    }

    @Override
    public List<EventDto> getAllSoonerEvents(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        List<Event> events = eventRepository.getAllSoonerEvents(startDateTime, endDateTime);

        if (events != null && !events.isEmpty()) {
            return events.stream()
                    .map(event -> EventMapper.mapToEventDto(event, new EventDto()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
