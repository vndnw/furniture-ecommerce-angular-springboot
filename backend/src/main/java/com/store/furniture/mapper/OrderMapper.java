package com.store.furniture.mapper;

import com.store.furniture.dto.request.OrderCreationRequest;
import com.store.furniture.dto.request.OrderUpdateRequest;
import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {

//    @Mapping(source = "customer.id", target = "customerId")
    OrderResponse toResponse(Order order);

//    @Mapping(source = "customer.id", target = "customer.id")
    Order toOrder(OrderCreationRequest request);

//    @Mapping(target = "customer", ignore = true)
    void updateOrder(@MappingTarget Order order, OrderUpdateRequest orderUpdateRequest);



}