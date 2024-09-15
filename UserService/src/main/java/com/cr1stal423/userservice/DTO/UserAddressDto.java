package com.cr1stal423.userservice.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAddressDto {
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "city is required")
    private String city;
    @NotBlank(message = "state is required")
    private String state;
    @NotBlank(message = "country is required")
    private String country;
}
