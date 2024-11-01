package com.cr1stal.eventservice.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailsDto {
    @NotNull(message = "category is required")
    private EventDto eventDto;
    @NotNull(message = "organizer is required")
    private OrganizerDto organizerDto;
    @NotNull(message = "category is required")
    private CategoryDto categoryDto;
}
