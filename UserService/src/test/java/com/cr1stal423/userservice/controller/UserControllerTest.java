package com.cr1stal423.userservice.controller;

import com.cr1stal423.userservice.DTO.UserAddressDto;
import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.DTO.UserProfileDto;
import com.cr1stal423.userservice.constants.UserConstants;
import com.cr1stal423.userservice.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private IUserService iUserService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Spy
    private UserDto userDto;

    private String userJson;

    private String profileJson;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
        userJson = """
                {
                    "username" : "cr1stal411",
                    "password" : "54321",
                    "email" : "vlad@gmail.com",
                    "firstName" : "Vlad",
                    "lastName" : "Gmail"
                }
                """;
        profileJson = """
                {
                    "bio" : "info about user",
                    "mobileNumber" : "0509138089"
                }
                """;
    }

    @Test
    void createUserTest() throws Exception {
        mockMvc.perform(post("/api/v1/users/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

    @Test
    void getUserTest() throws Exception {
        Mockito.when(iUserService.getUser(anyLong())).thenReturn(userDto);
        mockMvc.perform(get("/api/v1/users/getUser/1"))
                .andExpect(status().isOk());

        verify(iUserService, times(1)).getUser(anyLong());
    }

    @Test
    void getUserByEmailTest() throws Exception {
        Mockito.when(iUserService.getUserByEmail(anyString())).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/users/getUserByEmail")
                        .param("email", "vlad@gmail.com"))
                .andExpect(status().isOk());

        verify(iUserService, times(1)).getUserByEmail(anyString());
    }

    @Test
    void updateUserTest() throws Exception {
        when(iUserService.updateUser(any(UserDto.class))).thenReturn(true);
        userDto.setEmail("vlad@gmail.com");
        mockMvc.perform(put("/api/v1/users/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
        verify(iUserService, times(1)).updateUser(any(UserDto.class));
    }

    @Test
    void deleteUserTest() throws Exception {
        when(iUserService.deleteUser(anyLong())).thenReturn(true);
        userDto.setEmail("vlad@gmail.com");
        mockMvc.perform(delete("/api/v1/users/delete/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
        verify(iUserService, times(1)).deleteUser(anyLong());
    }

    @Test
    void setUserProfileTest() throws Exception {
        doNothing().when(iUserService).addUserProfile(any(UserProfileDto.class), anyLong());
        mockMvc.perform(post("/api/v1/users/setUserProfile/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(profileJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
        verify(iUserService, times(1)).addUserProfile(any(UserProfileDto.class), anyLong());
    }

    @Test
    void setUserAddressTest() throws Exception {
        UserAddressDto addressDto = new UserAddressDto();
        addressDto.setAddress("Test Street 123");
        addressDto.setCity("Kyiv");
        addressDto.setState("Kyivska");
        addressDto.setCountry("Ukraine");

        String addressJson = """
                {
                    "address": "Test Street 123",
                    "city": "Kyiv",
                    "state": "Kyivska",
                    "country": "Ukraine"
                }
                """;

        doNothing().when(iUserService).addUserAddress(any(UserAddressDto.class), anyLong());

        mockMvc.perform(post("/api/v1/users/setUserAddress/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addressJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

    @Test
    void getUserProfileTest() throws Exception {
        UserProfileDto profileDto = new UserProfileDto();
        profileDto.setBio("info about user");
        profileDto.setMobileNumber("0509138089");

        when(iUserService.getUserProfile(anyLong())).thenReturn(profileDto);

        mockMvc.perform(get("/api/v1/users/getUserProfile/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bio").value("info about user"))
                .andExpect(jsonPath("$.mobileNumber").value("0509138089"));
    }

    @Test
    void getUserAddressesTest() throws Exception {
        UserAddressDto addressDto = new UserAddressDto();
        addressDto.setAddress("Test Street");
        addressDto.setCity("Kyiv");
        addressDto.setState("Kyivska");
        addressDto.setCountry("Ukraine");

        when(iUserService.getUserAddress(anyLong())).thenReturn(List.of(addressDto));

        mockMvc.perform(get("/api/v1/users/getUserAddresses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("Kyiv"));
    }

    @Test
    void updateUserProfileTest() throws Exception {
        when(iUserService.updateUserProfile(any(UserProfileDto.class), anyLong())).thenReturn(true);

        mockMvc.perform(put("/api/v1/users/updateUserProfile/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

    @Test
    void updateUserAddressTest() throws Exception {
        when(iUserService.updateUserAddress(any(UserAddressDto.class), anyLong())).thenReturn(true);

        String addressJson = """
                {
                    "address": "New Address",
                    "city": "Lviv",
                    "state": "Lvivska",
                    "country": "Ukraine"
                }
                """;

        mockMvc.perform(put("/api/v1/users/updateUserAddress/1/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addressJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

    @Test
    void deleteUserProfileTest() throws Exception {
        when(iUserService.deleteUserProfile(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/users/deleteUserProfile/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

    @Test
    void deleteUserAddressTest() throws Exception {
        when(iUserService.deleteUserAddress(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/users/deleteUserAddress/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

    @Test
    void addRoleToUserTest() throws Exception {
        doNothing().when(iUserService).addRole(anyLong(), anyLong());

        mockMvc.perform(post("/api/v1/users/1/addRole/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

    @Test
    void deleteRoleFromUserTest() throws Exception {
        when(iUserService.deleteRole(anyLong(), anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/users/1/deleteRole/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(UserConstants.STATUS_200));
    }

}
