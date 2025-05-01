package com.cr1stal423.booking.exception;

public class UserLockedException extends RuntimeException{
    public UserLockedException(String userInfo, String message){
        super(String.format("User account with username %s is locked. %s", userInfo, message));
    }
}
