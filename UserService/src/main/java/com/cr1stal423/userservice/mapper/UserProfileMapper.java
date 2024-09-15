package com.cr1stal423.userservice.mapper;

import com.cr1stal423.userservice.DTO.UserProfileDto;
import com.cr1stal423.userservice.model.UserProfile;

public class UserProfileMapper {

    public static UserProfileDto mapToUserProfileDto(UserProfile userProfile, UserProfileDto userProfileDto){
        userProfileDto.setBio(userProfile.getBio());
        userProfileDto.setMobileNumber(userProfile.getMobileNumber());
        userProfileDto.setProfilePictureUrl(userProfile.getProfilePictureUrl());
        return userProfileDto;
    }

    public static UserProfile mapToUserProfile(UserProfileDto userProfileDto, UserProfile userProfile){
        userProfile.setBio(userProfileDto.getBio());
        userProfile.setMobileNumber(userProfileDto.getMobileNumber());
        userProfile.setProfilePictureUrl(userProfileDto.getProfilePictureUrl());
        return userProfile;
    }
}
