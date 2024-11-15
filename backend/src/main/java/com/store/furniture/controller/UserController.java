package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.UserCreationRequest;
import com.store.furniture.dto.request.UserUpdateRequest;
import com.store.furniture.dto.response.UserResponse;
import com.store.furniture.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        var user = userService.createUser(userCreationRequest);
        return ApiResponse.<UserResponse>builder().data(user).build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        var users = userService.getAllUsers();
        return ApiResponse.<List<UserResponse>>builder().data(users).build();
    }

    @GetMapping("/{id}")
    ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        var user = userService.getUserById(id);
        return ApiResponse.<UserResponse>builder().data(user).build();
    }

    @GetMapping("/me")
    ApiResponse<UserResponse> getMe() {
        var user = userService.getProfile();
        return ApiResponse.<UserResponse>builder().data(user).build();
    }

    @GetMapping("/search")
    ApiResponse<List<UserResponse>> searchUsers(@RequestParam String keyword) {
        var users = userService.searchUsers(keyword);
        return ApiResponse.<List<UserResponse>>builder().data(users).build();
    }

    @PutMapping("/changeRole/{id}")
    ApiResponse<UserResponse> changeRole(@PathVariable String id, @RequestParam String role) {
        var user = userService.changeRole(id, role);
        return ApiResponse.<UserResponse>builder().data(user).build();
    }

    @PutMapping("/{id}")
    ApiResponse<UserResponse> updateUser(
            @PathVariable String id, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        var user = userService.updateUser(id, userUpdateRequest);
        return ApiResponse.<UserResponse>builder().data(user).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<UserResponse> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ApiResponse.<UserResponse>builder().build();
    }
}
