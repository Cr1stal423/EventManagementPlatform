package com.cr1stal423.userservice.service;

import com.cr1stal423.userservice.DTO.UserAddressDto;
import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.DTO.UserProfileDto;

import java.util.List;

public interface IUserService {

    void createUser(UserDto userDto);

    UserDto getUser(Long userId);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(Long userId);

    void addUserProfile(UserProfileDto userProfileDto, Long userId);

    void addUserAddress(UserAddressDto userAddressDto, Long userId);

    UserProfileDto getUserProfile(Long userId);

    List<UserAddressDto> getUserAddress(Long userId);

    boolean updateUserProfile(UserProfileDto userProfileDto, Long userId);

    boolean updateUserAddress(UserAddressDto userAddressDto, Long addressId);

    boolean deleteUserProfile(Long userId);

    boolean deleteUserAddress(Long addressId);





}
