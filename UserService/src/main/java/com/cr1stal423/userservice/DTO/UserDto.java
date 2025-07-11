package com.cr1stal423.userservice.DTO;


import com.cr1stal423.userservice.model.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    private List<RoleDto> roles;

    private UserProfileDto userProfileDto;

    private List<UserAddressDto> userAddressDto;
}
