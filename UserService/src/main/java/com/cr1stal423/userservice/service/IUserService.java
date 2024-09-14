package com.cr1stal423.userservice.service;

import com.cr1stal423.userservice.DTO.UserDto;

public interface IUserService {
    void createUser(UserDto userDto);

    UserDto getUser(Long userId);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(Long userId);
}
