package com.cr1stal423.booking.service;

import com.cr1stal423.booking.DTO.BookingDto;
import com.cr1stal423.booking.DTO.PaymentDto;

public interface IBookingService {

    void createBooking(BookingDto bookingDto);

    PaymentDto fetchBooking(String bookingId);

    boolean updateBooking(PaymentDto paymentDto);

    boolean deleteBooking(String bookingId);
}
