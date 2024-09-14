package com.cr1stal423.userservice.mapper;

import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.model.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user, UserDto userDto){
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setStatus(user.getStatus());
        return userDto;
    }
    public static User mapToUser(UserDto userDto, User user){
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setStatus(userDto.getStatus());
        return user;
    }
}
