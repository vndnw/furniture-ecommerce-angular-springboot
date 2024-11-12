package com.store.furniture.mapper;

import com.store.furniture.dto.response.OrderItemResponse;
import com.store.furniture.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.id", target = "productId") // Map Product's ID to productId
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);



}