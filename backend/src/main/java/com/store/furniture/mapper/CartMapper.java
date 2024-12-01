package com.store.furniture.mapper;

import com.store.furniture.dto.response.CartResponse;
import com.store.furniture.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {CartItemMapper.class})
public interface CartMapper {

    @Mapping(source = "cartItems", target = "cartItems")
    CartResponse toResponse(Cart cart);
}
