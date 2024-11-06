package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.AdminCreationRequest;
import com.store.furniture.dto.request.AdminUpdateRequest;
import com.store.furniture.dto.response.AdminResponse;
import com.store.furniture.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    ApiResponse<AdminResponse> createAdmin(@RequestBody AdminCreationRequest adminCreationRequest) {
        var admin = adminService.createAdmin(adminCreationRequest);
        return ApiResponse.<AdminResponse>builder().data(admin).build();
    }

    @GetMapping
    ApiResponse<List<AdminResponse>> getAllAdmins() {
        var admins = adminService.getAllAdmins();
        return ApiResponse.<List<AdminResponse>>builder().data(admins).build();
    }

    @GetMapping("/{id}")
    ApiResponse<AdminResponse> getAdminById(@PathVariable String id) {
        var admin = adminService.getAdminById(id);
        return ApiResponse.<AdminResponse>builder().data(admin).build();
    }

    @PutMapping("/{id}")
    ApiResponse<AdminResponse> updateAdmin(@PathVariable String id, @RequestBody AdminUpdateRequest adminUpdateRequest) {
        var admin = adminService.updateAdmin(id, adminUpdateRequest);
        return ApiResponse.<AdminResponse>builder().data(admin).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<AdminResponse> deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
        return ApiResponse.<AdminResponse>builder().build();
    }
}
