package com.cr1stal423.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_profiles")
public class UserProfile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "bio",nullable = true)
    private String bio;
    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;
    @Column(name = "profile_picture_url", nullable = true)
    private String profilePictureUrl;
}
