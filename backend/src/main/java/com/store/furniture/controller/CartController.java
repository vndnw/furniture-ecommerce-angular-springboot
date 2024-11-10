package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.response.CartResponse;
import com.store.furniture.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public ApiResponse<CartResponse> getCartByCustomerId(@PathVariable String customerId) {
        CartResponse cartResponse = cartService.getCartByCustomerId(customerId);
        return ApiResponse.<CartResponse>builder()
                .data(cartResponse).build();
    }

    @PostMapping("/{customerId}/items")
    public ApiResponse<CartResponse> addItemToCart(
            @PathVariable String customerId,
            @RequestParam String productId,
            @RequestParam int quantity
    ) {
        CartResponse updatedCart = cartService.addItemToCart(customerId, productId, quantity);
        return ApiResponse.<CartResponse>builder()
                .data(updatedCart).build();
    }

    @PutMapping("/{customerId}/items")
    public ApiResponse<CartResponse> updateCartItem(
            @PathVariable String customerId,
            @RequestParam String productId,
            @RequestParam int quantity
    ) {
        CartResponse updatedCart = cartService.updateCartItem(customerId, productId, quantity);
        return ApiResponse.<CartResponse>builder()
                .data(updatedCart).build();
    }

    @DeleteMapping("/{customerId}/items/{productId}")
    public ApiResponse<CartResponse> removeItemFromCart(
            @PathVariable String customerId,
            @PathVariable String productId
    ) {
        CartResponse updatedCart = cartService.removeItemFromCart(customerId, productId);
        return ApiResponse.<CartResponse>builder()
                .data(updatedCart).build();
    }

}