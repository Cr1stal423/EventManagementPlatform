package com.cr1stal423.booking.DTO;

import com.cr1stal423.booking.constants.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {
    @NotBlank(message = "username is required")
    @Size(min = 3)
    private String username;
    @NotBlank(message = "password is required")
    @Size(min = 3)
    private String password;
    @Email
    private String email;
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;

    private UserStatus status;

}
