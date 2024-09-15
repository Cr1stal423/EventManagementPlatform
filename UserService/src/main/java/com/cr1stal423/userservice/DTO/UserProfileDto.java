package com.cr1stal423.userservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserProfileDto {
    @NotBlank(message = "bio is required")
    private String bio;
    @NotBlank(message = "mobile number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must have 10 digits")
    private String mobileNumber;

    private String profilePictureUrl;

}
