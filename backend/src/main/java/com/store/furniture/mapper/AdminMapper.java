package com.store.furniture.mapper;

import com.store.furniture.dto.request.AdminCreationRequest;
import com.store.furniture.dto.response.AdminResponse;
import com.store.furniture.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toAdmin(AdminCreationRequest adminCreationRequest);
    AdminResponse toAdminResponse(Admin admin);
}