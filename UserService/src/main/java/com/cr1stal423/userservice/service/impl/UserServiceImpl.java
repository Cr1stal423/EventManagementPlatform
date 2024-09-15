package com.cr1stal423.userservice.service.impl;

import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.constants.UserConstants;
import com.cr1stal423.userservice.exception.ResourceNotFoundException;
import com.cr1stal423.userservice.exception.UserAlreadyExistException;
import com.cr1stal423.userservice.mapper.UserMapper;
import com.cr1stal423.userservice.model.Role;
import com.cr1stal423.userservice.model.User;
import com.cr1stal423.userservice.repository.RoleRepository;
import com.cr1stal423.userservice.repository.UserRepository;
import com.cr1stal423.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto, new User());
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistException("User with given username already exist");
        }
        Role role = roleRepository.findByRoleName(UserConstants.USER_ROLE);
        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
        );
        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());
        return userDto;
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        boolean isUpdated = false;
        if (userDto != null) {
            User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", userDto.getUsername())
            );
            UserMapper.mapToUser(userDto, user);
            userRepository.save(user);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteUser(Long userId) {
        boolean isDeleted = false;
        if (userId != null) {
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
            );
            userRepository.delete(user);
            isDeleted = true;
        }
        return isDeleted;
    }
}
