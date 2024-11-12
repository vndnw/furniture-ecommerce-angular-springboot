package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.UserCreationRequest;
import com.store.furniture.dto.request.UserUpdateRequest;
import com.store.furniture.dto.response.UserResponse;
import com.store.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService adminService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest adminCreationRequest) {
        var user = adminService.createUser(adminCreationRequest);
        return ApiResponse.<UserResponse>builder().data(user).build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        var admins = adminService.getAllUsers();
        return ApiResponse.<List<UserResponse>>builder().data(admins).build();
    }

    @GetMapping("/{id}")
    ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        var admin = adminService.getUserById(id);
        return ApiResponse.<UserResponse>builder().data(admin).build();
    }

    @PutMapping("/{id}")
    ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest adminUpdateRequest) {
        var admin = adminService.updateUser(id, adminUpdateRequest);
        return ApiResponse.<UserResponse>builder().data(admin).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<UserResponse> deleteUser(@PathVariable String id) {
        adminService.deleteUser(id);
        return ApiResponse.<UserResponse>builder().build();
    }
}
