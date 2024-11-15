package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.OrderCreationRequest;
import com.store.furniture.dto.response.OrderResponse;
import com.store.furniture.dto.response.PaginatedResponse;
import com.store.furniture.enums.OrderStatus;
import com.store.furniture.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    ApiResponse<PaginatedResponse<OrderResponse>> getAllOrders(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        var orders = orderService.getAllOrders(page, size);
        return ApiResponse.<PaginatedResponse<OrderResponse>>builder()
                .data(orders)
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<OrderResponse> getOrderById(@PathVariable String id) {
        var order = orderService.getOrderById(id);
        return ApiResponse.<OrderResponse>builder().data(order).build();
    }

    @PutMapping("/{id}")
    ApiResponse<OrderResponse> updateOrderStatus(@PathVariable String id, @RequestParam OrderStatus status) {
        var order = orderService.updateOrderStatus(id, status);
        return ApiResponse.<OrderResponse>builder().data(order).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ApiResponse.<Void>builder().build();
    }
}
