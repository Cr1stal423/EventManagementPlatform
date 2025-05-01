package com.cr1stal423.booking.mapper;

import com.cr1stal423.booking.DTO.BookingDto;
import com.cr1stal423.booking.model.Booking;
import lombok.Data;

@Data
public class BookingMapper {
    public static BookingDto mapToBookingDto(Booking booking, BookingDto bookingDto){
        bookingDto.setBookingStatus(booking.getBookingStatus());
        bookingDto.setBookingTime(booking.getBookingTime());
        bookingDto.setEventId(booking.getEventId());
        bookingDto.setUserId(booking.getUserId());
        return bookingDto;
    }
    public static Booking mapToBooking(BookingDto bookingDto, Booking booking){
        booking.setBookingStatus(bookingDto.getBookingStatus());
        booking.setBookingTime(bookingDto.getBookingTime());
        booking.setEventId(bookingDto.getEventId());
        booking.setUserId(bookingDto.getUserId());
        return booking;
    }
}
