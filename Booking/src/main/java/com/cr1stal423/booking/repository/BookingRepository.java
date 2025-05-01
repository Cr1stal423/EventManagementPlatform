package com.cr1stal423.booking.repository;

import com.cr1stal423.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookingRepository extends JpaRepository<Booking,Long> {
}
