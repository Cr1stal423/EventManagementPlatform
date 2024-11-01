package com.cr1stal.eventservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDateTime eventDate;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int availableSeats;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer duration;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne()
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setEvent(this);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        ticket.setEvent(null);
    }
}
