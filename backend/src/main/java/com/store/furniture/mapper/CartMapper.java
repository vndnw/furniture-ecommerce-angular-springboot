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
    @Mapping(
            target = "totalAmount",
            expression =
                    "java(cart.getCartItems().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum())")
    CartResponse toResponse(Cart cart);
}
