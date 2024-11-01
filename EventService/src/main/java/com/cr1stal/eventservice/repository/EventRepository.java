package com.cr1stal.eventservice.repository;

import com.cr1stal.eventservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Set<Event> getAllByCategoryCategoryNameEquals(String categoryName);
    @Query("select distinct e from Event as e where e.eventDate between ?1 and ?2")
    List<Event> getAllSoonerEvents(LocalDate startDate, LocalDate endDate);
}
