package com.store.furniture.service;

import com.store.furniture.dto.request.AdminCreationRequest;
import com.store.furniture.dto.response.AdminResponse;
import com.store.furniture.entity.Admin;
import com.store.furniture.enums.Role;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.AdminMapper;
import com.store.furniture.repository.AdminRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminService {
    AdminRepository adminRepository;
    AdminMapper adminMapper;


    public AdminResponse createAdmin(AdminCreationRequest adminCreationRequest) {
        if (adminRepository.existsByUsername(adminCreationRequest.getUsername()))
            throw new AppException(ErrorCode.USERNAME_EXISTS);
        if (adminRepository.existsByEmail(adminCreationRequest.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTS);

        Admin admin = adminMapper.toAdmin(adminCreationRequest);
        admin.setRole(Role.ADMIN.name());
        return adminMapper.toAdminResponse(adminRepository.save(admin));
    }

    public List<AdminResponse> getAllAdmins() {
        return adminRepository.findAll().stream().map(adminMapper::toAdminResponse).toList();
    }
}
