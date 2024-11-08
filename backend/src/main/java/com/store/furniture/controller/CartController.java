package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.CartCreationRequest;
import com.store.furniture.dto.response.CartResponse;
import com.store.furniture.entity.Cart;
import com.store.furniture.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {
    CartService cartService;

    @PostMapping
    ApiResponse<CartResponse> createCart(@RequestBody CartCreationRequest cartCreationRequest) {
        var cart = cartService.createCart(cartCreationRequest);
        return ApiResponse.<CartResponse>builder()
                .data(cart).build();
    }

    @GetMapping("/{id}")
    ApiResponse<CartResponse> getCartById(@PathVariable String id) {
        var cart = cartService.getCartById(id);
        return ApiResponse.<CartResponse>builder()
                .data(cart).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return ApiResponse.<Void>builder().build();
    }
}