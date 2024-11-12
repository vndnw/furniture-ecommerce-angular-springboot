package com.store.furniture.service;

import com.store.furniture.dto.request.UserCreationRequest;
import com.store.furniture.dto.request.UserUpdateRequest;
import com.store.furniture.dto.response.UserResponse;
import com.store.furniture.entity.User;
import com.store.furniture.enums.Role;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.UserMapper;
import com.store.furniture.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest adminCreationRequest) {
        if (userRepository.existsByUsername(adminCreationRequest.getUsername()))
            throw new AppException(ErrorCode.USERNAME_EXISTS);
        if (userRepository.existsByEmail(adminCreationRequest.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTS);

        User user = userMapper.toUser(adminCreationRequest);
        user.setRole(Role.USER.name());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toResponse(userRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }
    
    public UserResponse getUserById(String id) {
         User admin = userRepository.findById(id)
                 .orElseThrow(
                 () -> new AppException(ErrorCode.USERNAME_EXISTS)
         );
         return userMapper.toResponse(admin);
    }

    public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest) {
        User admin = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTS)
        );

        userMapper.updateUser(admin, userUpdateRequest);
        return userMapper.toResponse(userRepository.save(admin));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
