package com.store.furniture.mapper;

import com.store.furniture.dto.request.OrderCreationRequest;
import com.store.furniture.dto.request.OrderUpdateRequest;
import com.store.furniture.dto.response.CartItemResponse;
import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.entity.CartItem;
import com.store.furniture.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    CartItemResponse toResponse(CartItem cartItem);
}