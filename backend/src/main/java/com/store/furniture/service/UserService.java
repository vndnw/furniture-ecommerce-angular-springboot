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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword).stream().map(userMapper::toResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse changeRole(String id, String role) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTS)
        );
        try {
            Role.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_ROLE);
        }
        user.setRole(role);
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest) {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        User authenticatedUser = userRepository.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        boolean isAdmin = authenticatedUser.getRole().equals("ADMIN");

        if (!isAdmin && !authenticatedUser.getId().equals(id)) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }


        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTS)
        );

        userMapper.updateUser(user, userUpdateRequest);
        return userMapper.toResponse(userRepository.save(user));
    }
    public UserResponse getProfile() {
        var userDetails = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(userDetails.getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
        return userMapper.toResponse(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}