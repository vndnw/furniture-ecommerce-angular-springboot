package com.store.furniture.mapper;

import com.store.furniture.dto.request.AdminCreationRequest;
import com.store.furniture.dto.request.AdminUpdateRequest;
import com.store.furniture.dto.response.AdminResponse;
import com.store.furniture.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toAdmin(AdminCreationRequest adminCreationRequest);

    void updateAdmin(@MappingTarget Admin admin , AdminUpdateRequest AdminUpdateRequest);
    AdminResponse toAdminResponse(Admin admin);
}
