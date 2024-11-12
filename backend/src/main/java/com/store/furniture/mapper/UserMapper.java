package com.store.furniture.mapper;

import com.store.furniture.dto.request.UserCreationRequest;
import com.store.furniture.dto.request.UserUpdateRequest;
import com.store.furniture.dto.response.UserResponse;
import com.store.furniture.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest adminCreationRequest);

    void updateUser(@MappingTarget User user , UserUpdateRequest AdminUpdateRequest);
    UserResponse toResponse(User admin);
}
