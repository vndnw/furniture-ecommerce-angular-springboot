package com.store.furniture.mapper;

import com.store.furniture.dto.request.OrderCreationRequest;
import com.store.furniture.dto.request.OrderUpdateRequest;
import com.store.furniture.dto.response.OrderItemResponse;
import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.entity.Order;
import com.store.furniture.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.id", target = "productId") // Map Product's ID to productId
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);



}