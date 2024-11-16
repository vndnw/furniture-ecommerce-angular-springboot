package com.store.furniture.mapper;

import com.store.furniture.dto.request.OrderCreationRequest;
import com.store.furniture.dto.request.OrderUpdateRequest;
import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "user", ignore = true)
    Order toOrder(OrderCreationRequest orderCreationRequest);

    void updateOrder(@MappingTarget Order order, OrderUpdateRequest orderUpdateRequest);

    @Mapping(source = "order.user.id", target = "userId")
    OrderResponse toResponse(Order order);
}
