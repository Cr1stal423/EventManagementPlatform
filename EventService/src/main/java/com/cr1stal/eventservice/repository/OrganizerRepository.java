package com.cr1stal.eventservice.repository;

import com.cr1stal.eventservice.model.Event;
import com.cr1stal.eventservice.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer,Long> {
    Optional<Organizer> findByEmail(String email);

    @Query("select o from Organizer o where o.email = ?1")
    Set<Event> getAllEventsByOrganizer(String organizerEmail);
}
