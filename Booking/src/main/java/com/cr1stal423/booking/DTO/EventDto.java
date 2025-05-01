package com.cr1stal423.booking.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    @NotBlank(message = "title is required")
    private String title;
    @NotNull(message = "eventDate is required")
    private LocalDateTime eventDate;
    @NotBlank(message = "location is required")
    private String location;
    @NotNull(message = "availableSeats is required")
    @Size(min = 1, message = "availableSeats must be greater than 0")
    private int availableSeats;
    @NotBlank(message = "description is required")
    @Size(min = 1,max = 250, message = "description must be greater than 0 and less than 250 characters")
    private String description;
    @NotNull(message = "duration is required")
    private Integer duration;
}
