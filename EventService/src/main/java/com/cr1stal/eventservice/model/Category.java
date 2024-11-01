package com.cr1stal.eventservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Event> events;
}
