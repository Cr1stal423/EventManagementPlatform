package com.cr1stal423.userservice.controller;

import com.cr1stal423.userservice.DTO.ResponseDto;
import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.DTO.UserProfileDto;
import com.cr1stal423.userservice.constants.UserConstants;
import com.cr1stal423.userservice.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_200,UserConstants.MESSAGE_200));
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        UserDto userDto = userService.getUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(@Valid @RequestBody UserDto userDto){
        boolean isUpdated = userService.updateUser(userDto);
        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200,UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ResponseDto(UserConstants.STATUS_422,UserConstants.MESSAGE_422));
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable("id") Long id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200,UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(UserConstants.STATUS_500,UserConstants.MESSAGE_500));
        }
    }
    @PostMapping("setUserProfile/{id}")
    public ResponseEntity<ResponseDto> setUserProfile(@PathVariable("id") Long userId,
                                                      @Valid @RequestBody UserProfileDto userProfileDto){
        userService.addUserProfile(userProfileDto,userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(UserConstants.STATUS_200,UserConstants.MESSAGE_200));
    }
}
