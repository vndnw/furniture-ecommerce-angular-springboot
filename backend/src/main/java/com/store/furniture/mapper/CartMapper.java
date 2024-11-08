package com.store.furniture.mapper;

import com.store.furniture.dto.request.CartCreationRequest;
import com.store.furniture.dto.request.ProductCreationRequest;
import com.store.furniture.dto.request.ProductUpdateRequest;
import com.store.furniture.dto.response.CartResponse;
import com.store.furniture.dto.response.ProductResponse;
import com.store.furniture.entity.Cart;
import com.store.furniture.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartMapper {

//    @Mapping(source = "categoryId", target = "category.id")
    Cart tocCart(CartCreationRequest cartCreationRequest);

//    @Mapping(source = "categoryId", target = "category.id")
//    @Mapping(source = "category.id", target = "categoryId")

//    @Mapping(source = "category.id", target = "categoryId")
    CartResponse toCartResponse(Cart cart);
}
