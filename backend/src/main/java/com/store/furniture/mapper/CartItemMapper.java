package com.store.furniture.mapper;

import com.store.furniture.dto.response.CartItemResponse;
import com.store.furniture.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    CartItemResponse toResponse(CartItem cartItem);
}