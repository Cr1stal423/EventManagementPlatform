package com.cr1stal423.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_address")
public class UserAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false, name = "address")
    private String address;
    @Column(nullable = false, name = "city")
    private String city;
    @Column(nullable = false, name = "state")
    private String state;
    @Column(nullable = false ,name = "country")
    private String country;
}
