package com.cr1stal423.booking.mapper;

import com.cr1stal423.booking.DTO.TicketDto;
import com.cr1stal423.booking.model.Ticket;
import lombok.Data;

@Data
public class TicketMapper {
    public TicketDto mapToTicketDto(Ticket ticket, TicketDto ticketDto){
        ticketDto.setTicketNumber(ticket.getTicketNumber());
        return ticketDto;
    }
    public Ticket mapToTicket(TicketDto ticketDto, Ticket ticket){
        ticket.setTicketNumber(ticketDto.getTicketNumber());
        return ticket;
    }
}
