package com.cr1stal423.booking.service.impl;

import com.cr1stal423.booking.DTO.BookingDto;
import com.cr1stal423.booking.DTO.EventDto;
import com.cr1stal423.booking.DTO.PaymentDto;
import com.cr1stal423.booking.DTO.UserDto;
import com.cr1stal423.booking.mapper.BookingMapper;
import com.cr1stal423.booking.model.Booking;
import com.cr1stal423.booking.repository.BookingRepository;
import com.cr1stal423.booking.service.IBookingService;
import com.cr1stal423.booking.service.client.EventClient;
import com.cr1stal423.booking.service.client.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IBookingServiceImpl implements IBookingService {
    private UserClient userClient;
    private EventClient eventClient;
    private BookingRepository bookingRepository;

    @Override
    public void createBooking(BookingDto bookingDto) {
        Booking booking = BookingMapper.mapToBooking(bookingDto, new Booking());
        ResponseEntity<UserDto> userDto = userClient.getUser(bookingDto.getUserId());
        ResponseEntity<EventDto> eventDto = eventClient.getEventById(bookingDto.getEventId());

    }

    @Override
    public PaymentDto fetchBooking(String bookingId) {
        return null;
    }

    @Override
    public boolean updateBooking(PaymentDto paymentDto) {
        return false;
    }

    @Override
    public boolean deleteBooking(String bookingId) {
        return false;
    }
}
