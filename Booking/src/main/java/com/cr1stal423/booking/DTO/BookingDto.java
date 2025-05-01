package com.cr1stal423.booking.DTO;

import com.cr1stal423.booking.model.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class BookingDto {

    private PaymentDto paymentDto;

    private List<TicketDto> ticketDtoList;
    @NotNull
    private Long userId;
    @NotNull
    private Long eventId;

    private LocalDateTime bookingTime;

    @NotNull
    private BookingStatus bookingStatus;
}
