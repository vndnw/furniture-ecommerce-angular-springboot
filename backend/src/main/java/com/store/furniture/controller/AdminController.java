package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.AdminCreationRequest;
import com.store.furniture.dto.response.AdminResponse;
import com.store.furniture.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    ApiResponse<AdminResponse> createAdmin(@RequestBody AdminCreationRequest adminCreationRequest) {
        var admin = adminService.createAdmin(adminCreationRequest);
        return ApiResponse.<AdminResponse>builder()
                .data(admin)
                .build();
    }
    @GetMapping
    ApiResponse<List<AdminResponse>> getAllAdmins() {
        var admins = adminService.getAllAdmins();
        return ApiResponse.<List<AdminResponse>>builder()
                .data(admins).build();
    }

}
