package com.cr1stal423.userservice.controller;

import com.cr1stal423.userservice.DTO.ResponseDto;
import com.cr1stal423.userservice.DTO.UserAddressDto;
import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.DTO.UserProfileDto;
import com.cr1stal423.userservice.constants.UserConstants;
import com.cr1stal423.userservice.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        UserDto userDto = userService.getUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(@Valid @RequestBody UserDto userDto) {
        boolean isUpdated = userService.updateUser(userDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ResponseDto(UserConstants.STATUS_422, UserConstants.MESSAGE_422));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable("id") Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(UserConstants.STATUS_500, UserConstants.MESSAGE_500));
        }
    }

    @PostMapping("setUserProfile/{id}")
    public ResponseEntity<ResponseDto> setUserProfile(@PathVariable("id") Long userId,
                                                      @Valid @RequestBody UserProfileDto userProfileDto) {
        userService.addUserProfile(userProfileDto, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    @PostMapping("setUserAddress/{id}")
    public ResponseEntity<ResponseDto> setUserAddress(@PathVariable("id") Long userId,
                                                      @Valid @RequestBody UserAddressDto userAddressDto) {
        userService.addUserAddress(userAddressDto, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    @GetMapping("/getUserProfile/{id}")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable("id") Long userId) {
        UserProfileDto userProfileDto = userService.getUserProfile(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileDto);
    }

    @GetMapping("/getUserAddresses/{id}")
    public ResponseEntity<List<UserAddressDto>> getUserAddresses(@PathVariable("id") Long userId) {
        List<UserAddressDto> userAddressDtoList = userService.getUserAddress(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userAddressDtoList);
    }

    @PutMapping("/updateUserProfile/{id}")
    public ResponseEntity<ResponseDto> updateUserProfile(@PathVariable("id") Long userId,
                                                         @Valid @RequestBody UserProfileDto userProfileDto) {
        boolean isUpdated = userService.updateUserProfile(userProfileDto, userId);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ResponseDto(UserConstants.STATUS_422, UserConstants.MESSAGE_422));

    }

    @PutMapping("/updateUserAddress/{userid}/{addressId}")
    public ResponseEntity<ResponseDto> updateUserAddress(@PathVariable("userid") Long userId,
                                                         @PathVariable("addressId") Long addressId,
                                                         @Valid @RequestBody UserAddressDto userAddressDto) {
        boolean isUpdated = userService.updateUserAddress(userAddressDto, addressId);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ResponseDto(UserConstants.STATUS_422, UserConstants.MESSAGE_422));
    }

    @DeleteMapping("/deleteUserProfile/{id}")
    public ResponseEntity<ResponseDto> deleteUserProfile(@PathVariable("id") Long userId) {
        boolean isDeleted = userService.deleteUserProfile(userId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ResponseDto(UserConstants.STATUS_422, UserConstants.MESSAGE_422));
    }

    @DeleteMapping("/deleteUserAddress/{id}")
    public ResponseEntity<ResponseDto> deleteUserAddress(@PathVariable("id") Long addressId) {
        boolean isDeleted = userService.deleteUserAddress(addressId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ResponseDto(UserConstants.STATUS_422, UserConstants.MESSAGE_422));
    }

    @PostMapping("/{userId}/addRole/{roleId}")
    public ResponseEntity<ResponseDto> addRoleToUser(@PathVariable("userId") Long userId,
                                                     @PathVariable("roleId") Long roleId) {
        userService.addRole(userId, roleId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }
    @DeleteMapping("/{userId}/deleteRole/{roleId}")
    public ResponseEntity<ResponseDto> deleteRole(@PathVariable("userId") Long userId,
                                                  @PathVariable("roleId") Long roleId) {
        userService.deleteRole(userId, roleId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }
}
