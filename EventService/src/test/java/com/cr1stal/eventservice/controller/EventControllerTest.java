package com.cr1stal.eventservice.controller;

import com.cr1stal.eventservice.DTO.EventDetailsDto;
import com.cr1stal.eventservice.DTO.EventDto;
import com.cr1stal.eventservice.constants.EventConstants;
import com.cr1stal.eventservice.service.IEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    private IEventService eventService;

    @InjectMocks
    private EventController eventController;

    private MockMvc mockMvc;

    private String eventDetailsDto;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();

        eventDetailsDto = """
                {
                    "eventDto":{
                        "title": "Title",
                        "eventDate": "2024-12-31T14:30:00",
                        "location": "Berlin",
                        "availbaleSeats": 100,
                        "description": "Description",
                        "duration": 120
                    },
                    "organizerDto":{
                        "firstName": "Vladyslav",
                        "lastName" : "Haliara",
                        "email" : "vlad.galyara@gmail.com"
                    },
                    "categoryDto":{
                        "categoryName": "Category1"
                    }
                }
                """;
    }

    @Test
    void createEventTest() throws Exception {
        doNothing().when(eventService).createEvent(any(EventDetailsDto.class));

        mockMvc.perform(post("/api/v1/events/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(eventDetailsDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(EventConstants.STATUS_201));

        verify(eventService, times(1)).createEvent(any(EventDetailsDto.class));
    }

    @Test
    void getEventById() throws Exception {
        when(eventService.getEventById(anyLong())).thenReturn(new EventDto());
        mockMvc.perform(get("/api/v1/events/getById/1"))
                .andExpect(status().isOk());
        verify(eventService, times(1)).getEventById(anyLong());
    }

    @Test
    void getAllOrganizerEventsTest() throws Exception {
        when(eventService.getAllEventsByOrganizer(anyString())).thenReturn(Set.of(new EventDto()));

        mockMvc.perform(get("/api/v1/events/getAllOrganizerEvents/vlad.galyara@gmail.com"))
                .andExpect(status().isOk());
        verify(eventService, times(1)).getAllEventsByOrganizer(anyString());
    }

    @Test
    void getAllSoonerEventsTest() throws Exception {
        when(eventService.getAllSoonerEvents(any(), any()))
                .thenReturn(List.of(new EventDto()));

        mockMvc.perform(get("/api/v1/events/getAllSoonerEvents")
                        .param("startDate", "2024-12-01")
                        .param("endDate", "2024-12-31"))
                .andExpect(status().isOk());

        verify(eventService, times(1)).getAllSoonerEvents(any(), any());
    }

    @Test
    void updateEventTest_success() throws Exception {
        when(eventService.updateEvent(any(EventDto.class), anyLong())).thenReturn(true);

        String eventDto = """
                {
                    "title": "New Title",
                    "eventDate": "2024-12-31T14:30:00",
                    "location": "Berlin",
                    "availbaleSeats": 100,
                    "description": "Updated Description",
                    "duration": 120
                }
                """;

        mockMvc.perform(put("/api/v1/events/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(EventConstants.STATUS_200));

        verify(eventService, times(1)).updateEvent(any(EventDto.class), anyLong());
    }

    @Test
    void updateEventTest_failed() throws Exception {
        when(eventService.updateEvent(any(EventDto.class), anyLong())).thenReturn(false);

        String eventDto = """
                {
                    "title": "New Title",
                    "eventDate": "2024-12-31T14:30:00",
                    "location": "Berlin",
                    "availbaleSeats": 100,
                    "description": "Updated Description",
                    "duration": 120
                }
                """;

        mockMvc.perform(put("/api/v1/events/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventDto))
                .andExpect(status().isExpectationFailed())
                .andExpect(jsonPath("$.statusCode").value(EventConstants.STATUS_417));

        verify(eventService, times(1)).updateEvent(any(EventDto.class), anyLong());
    }

    @Test
    void deleteEventTest_success() throws Exception {
        when(eventService.deleteEvent(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/events/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(EventConstants.STATUS_200));

        verify(eventService, times(1)).deleteEvent(anyLong());
    }

    @Test
    void deleteEventTest_failed() throws Exception {
        when(eventService.deleteEvent(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/api/v1/events/delete/1"))
                .andExpect(status().isExpectationFailed())
                .andExpect(jsonPath("$.statusCode").value(EventConstants.STATUS_417));

        verify(eventService, times(1)).deleteEvent(anyLong());
    }


}

