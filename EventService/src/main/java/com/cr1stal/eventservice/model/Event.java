package com.cr1stal.eventservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne()
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

}
