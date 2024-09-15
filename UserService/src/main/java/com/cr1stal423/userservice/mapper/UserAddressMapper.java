package com.cr1stal423.userservice.mapper;

import com.cr1stal423.userservice.DTO.UserAddressDto;
import com.cr1stal423.userservice.model.UserAddress;

public class UserAddressMapper {
    public static UserAddressDto mapToUserAddressDto(UserAddress userAddress, UserAddressDto userAddressDto) {
        userAddressDto.setAddress(userAddress.getAddress());
        userAddressDto.setCity(userAddress.getCity());
        userAddressDto.setState(userAddress.getState());
        userAddressDto.setCountry(userAddress.getCountry());
        return userAddressDto;
    }
    public static UserAddress mapToUserAddress(UserAddressDto userAddressDto, UserAddress userAddress) {
        userAddress.setAddress(userAddressDto.getAddress());
        userAddress.setCity(userAddressDto.getCity());
        userAddress.setState(userAddressDto.getState());
        userAddress.setCountry(userAddressDto.getCountry());
        return userAddress;
    }
    public static UserAddressDto mapToUserAddressDto(UserAddress userAddress){
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setAddress(userAddress.getAddress());
        userAddressDto.setCity(userAddress.getCity());
        userAddressDto.setState(userAddress.getState());
        userAddressDto.setCountry(userAddress.getCountry());
        return userAddressDto;
    }

}
