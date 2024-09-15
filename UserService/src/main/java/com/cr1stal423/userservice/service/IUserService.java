package com.cr1stal423.userservice.service;

import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.DTO.UserProfileDto;

public interface IUserService {

    void createUser(UserDto userDto);

    UserDto getUser(Long userId);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(Long userId);

    void addUserProfile(UserProfileDto userProfileDto, Long userId);

}
