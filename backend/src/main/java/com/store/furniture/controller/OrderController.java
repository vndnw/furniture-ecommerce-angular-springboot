package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.OrderCreationRequest;
import com.store.furniture.dto.request.OrderUpdateRequest;
import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    ApiResponse<OrderResponse> createOrder(@RequestBody OrderCreationRequest request) {
        var order = orderService.createOrder(request);
        return ApiResponse.<OrderResponse>builder().data(order).build();
    }

    @GetMapping
    ApiResponse<List<OrderResponse>> getAllOrders() {
        var orders = orderService.getAllOrders();
        return ApiResponse.<List<OrderResponse>>builder().data(orders).build();
    }

    @GetMapping("/{id}")
    ApiResponse<OrderResponse> getOrderById(@PathVariable String id) {
        var order = orderService.getOrderById(id);
        return ApiResponse.<OrderResponse>builder().data(order).build();
    }

    @PutMapping("/{id}")
    ApiResponse<OrderResponse> updateOrder(@PathVariable String id, @RequestBody OrderUpdateRequest request) {
        var order = orderService.updateOrder(id, request);
        return ApiResponse.<OrderResponse>builder().data(order).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ApiResponse.<Void>builder().build();
    }
}