package com.cr1stal423.userservice.service.impl;

import com.cr1stal423.userservice.DTO.UserAddressDto;
import com.cr1stal423.userservice.DTO.UserDto;
import com.cr1stal423.userservice.DTO.UserProfileDto;
import com.cr1stal423.userservice.constants.UserConstants;
import com.cr1stal423.userservice.exception.ResourceNotFoundException;
import com.cr1stal423.userservice.exception.UserAlreadyExistException;
import com.cr1stal423.userservice.mapper.UserAddressMapper;
import com.cr1stal423.userservice.mapper.UserMapper;
import com.cr1stal423.userservice.mapper.UserProfileMapper;
import com.cr1stal423.userservice.model.Role;
import com.cr1stal423.userservice.model.User;
import com.cr1stal423.userservice.model.UserAddress;
import com.cr1stal423.userservice.model.UserProfile;
import com.cr1stal423.userservice.repository.RoleRepository;
import com.cr1stal423.userservice.repository.UserAddressRepository;
import com.cr1stal423.userservice.repository.UserProfileRepository;
import com.cr1stal423.userservice.repository.UserRepository;
import com.cr1stal423.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserAddressRepository userAddressRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userAddressRepository = userAddressRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto, new User());
        Optional<User> usernameOptional = userRepository.findByUsername(user.getUsername());
        Optional<User> emailOptional = userRepository.findByEmail(user.getEmail());
        if (usernameOptional.isPresent()) {
            throw new UserAlreadyExistException("User with given username already exist");
        }
        if (emailOptional.isPresent()) {
            throw new UserAlreadyExistException("User with given email already exist");
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
        userDto.setUserProfileDto(getUserProfileDto(userId));
        return userDto;
    }
    public UserProfileDto getUserProfileDto(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
        );
        Optional<UserProfile> userProfile = Optional.ofNullable(user.getProfile());
        if (!userProfile.isEmpty()){
            UserProfileDto userProfileDto = UserProfileMapper.mapToUserProfileDto(userProfile.get(), new UserProfileDto());
            return userProfileDto;
        }
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        String userEmail = email;
        userEmail.replaceFirst("%40", "@");
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email)
        );
        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());
        userDto.setUserProfileDto(getUserProfileDto(user.getId()));
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

    @Override
    public void addUserProfile(UserProfileDto userProfileDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
        );
        if (user.getProfile() == null) {
            UserProfile userProfile = UserProfileMapper.mapToUserProfile(userProfileDto, new UserProfile());
            user.setProfile(userProfile);
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException("User profile already exist");
        }
    }

    @Override
    public void addUserAddress(UserAddressDto userAddressDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
        );
        UserAddress userAddress = UserAddressMapper.mapToUserAddress(userAddressDto, new UserAddress());
//        user.addAddress(userAddress);
        userAddress.setUser(user);
        userAddressRepository.save(userAddress);
//        userRepository.save(user);
    }

    @Override
    public UserProfileDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
        );
        UserProfile userProfile = user.getProfile();
        UserProfileDto userProfileDto = UserProfileMapper.mapToUserProfileDto(userProfile, new UserProfileDto());

        return userProfileDto;
    }

    //    @Override
//    public List<UserAddressDto> getUserAddress(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
//        );
//        List<UserAddress> userAddresses = user.getAddress();
//        List<UserAddressDto> userAddressDtoList = new ArrayList<>();
//        userAddresses.forEach(userAddress -> {
//            UserAddressDto userAddressDto = UserAddressMapper.mapToUserAddressDto(userAddress, new UserAddressDto());
//            userAddressDtoList.add(userAddressDto);
//        });
//        return userAddressDtoList;
//    }
    @Override
    public List<UserAddressDto> getUserAddress(Long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getAddress().stream()
                        .map(UserAddressMapper::mapToUserAddressDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(userId)));
    }


    @Override
    public boolean updateUserProfile(UserProfileDto userProfileDto, Long userId) {
        boolean isUpdated = true;
        if (userProfileDto != null) {
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
            );
            UserProfile userProfile = user.getProfile();
            UserProfileMapper.mapToUserProfile(userProfileDto, userProfile);
            user.setProfile(userProfile);
            userRepository.save(user);
            isUpdated = true;
        }
        return isUpdated;
    }


    @Override
    public boolean updateUserAddress(UserAddressDto userAddressDto, Long addressId) {
        if (userAddressDto == null) {
            return false;
        }
        UserAddress userAddress = userAddressRepository.findById(addressId).orElseThrow(
                () -> new ResourceNotFoundException("UserAddress", "id", String.valueOf(addressId))
        );
        UserAddress updatedAddress = UserAddressMapper.mapToUserAddress(userAddressDto, userAddress);
        userAddressRepository.save(updatedAddress);
        return true;
    }

    @Override
    public boolean deleteUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
        );
        UserProfile userProfile = user.getProfile();
        if (userProfile != null) {
            user.setProfile(null);
            userRepository.save(user);
            userProfileRepository.delete(userProfile);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteUserAddress(Long addressId) {
        UserAddress address = userAddressRepository.findById(addressId).orElseThrow(
                () -> new ResourceNotFoundException("UserAddress", "id", String.valueOf(addressId))
        );
        User user = address.getUser();
        user.removeAddress(address);
        userAddressRepository.delete(address);
        return true;
    }

    @Override
    public void addRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
        );
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "id", String.valueOf(roleId))
        );
        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public boolean deleteRole(Long userId, Long roleId) {
        boolean isDeleted = true;
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", String.valueOf(userId))
        );
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "id", String.valueOf(roleId))
        );
        user.removeRole(role);
        userRepository.save(user);
        return isDeleted;
    }


}
