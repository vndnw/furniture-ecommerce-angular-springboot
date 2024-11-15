package com.store.furniture.mapper;

import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
@Mapping(source = "order.user.id", target = "userId")
    OrderResponse toResponse(Order order);
}