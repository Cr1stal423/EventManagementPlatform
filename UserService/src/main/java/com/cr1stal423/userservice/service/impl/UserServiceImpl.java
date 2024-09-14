package com.cr1stal423.userservice.service.impl;

import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.exception.UserAlreadyExistException;
import com.cr1stal423.userservice.mapper.UserMapper;
import com.cr1stal423.userservice.model.User;
import com.cr1stal423.userservice.repository.UserRepository;
import com.cr1stal423.userservice.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto,new User());
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()){
            throw new UserAlreadyExistException("User with given username already exist");
        }
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(Long userId) {
        return null;
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        return false;
    }

    @Override
    public boolean deleteUser(Long userId) {
        return false;
    }
}
