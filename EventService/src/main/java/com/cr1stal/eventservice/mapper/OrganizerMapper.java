package com.cr1stal.eventservice.mapper;

import com.cr1stal.eventservice.DTO.OrganizerDto;
import com.cr1stal.eventservice.model.Organizer;

public class OrganizerMapper {
    public static OrganizerDto mapToOrganizerDto(Organizer organizer, OrganizerDto organizerDto){
        organizerDto.setEmail(organizer.getEmail());
        organizerDto.setFirstName(organizer.getFirstName());
        organizerDto.setLastName(organizer.getLastName());
        return organizerDto;
    }
    public static Organizer mapToOrganizer(OrganizerDto organizerDto, Organizer organizer){
        organizer.setEmail(organizerDto.getEmail());
        organizer.setFirstName(organizerDto.getFirstName());
        organizer.setLastName(organizerDto.getLastName());
        return organizer;
    }
}
