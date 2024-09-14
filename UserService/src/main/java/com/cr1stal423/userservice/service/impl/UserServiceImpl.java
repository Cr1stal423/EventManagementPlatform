package com.cr1stal423.userservice.service.impl;

import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.constants.UserConstants;
import com.cr1stal423.userservice.exception.UserAlreadyExistException;
import com.cr1stal423.userservice.mapper.UserMapper;
import com.cr1stal423.userservice.model.Role;
import com.cr1stal423.userservice.model.User;
import com.cr1stal423.userservice.repository.RoleRepository;
import com.cr1stal423.userservice.repository.UserRepository;
import com.cr1stal423.userservice.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto,new User());
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()){
            throw new UserAlreadyExistException("User with given username already exist");
        }
        Role role = roleRepository.findByRoleName(UserConstants.USER_ROLE);
        user.addRole(role);
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
