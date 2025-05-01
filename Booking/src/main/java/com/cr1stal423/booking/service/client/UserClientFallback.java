package com.cr1stal423.booking.service.client;

import com.cr1stal423.booking.DTO.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserClientFallback implements UserClient{
    
    public List<Integer> list = new ArrayList<>();

    public int[] array = new int[];

    public Integer doSomething(){
        list.set()
    }

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
        return null;
    }
}
