package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.ItemToCartRequest;
import com.store.furniture.dto.response.CartResponse;
import com.store.furniture.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ApiResponse<CartResponse> getCartByCustomerId() {
        CartResponse cartResponse = cartService.getCartByCustomerId();
        return ApiResponse.<CartResponse>builder().data(cartResponse).build();
    }

    @PostMapping("/items")
    public ApiResponse<CartResponse> addItemToCart(@RequestBody @Valid ItemToCartRequest itemToCartRequest) {
        CartResponse updatedCart = cartService.addItemToCart(itemToCartRequest);
        return ApiResponse.<CartResponse>builder().data(updatedCart).build();
    }

    @PutMapping("/items")
    public ApiResponse<CartResponse> updateCartItem(@RequestBody @Valid ItemToCartRequest itemToCartRequest) {
        CartResponse updatedCart = cartService.updateCartItem(itemToCartRequest);
        return ApiResponse.<CartResponse>builder().data(updatedCart).build();
    }

    @DeleteMapping("/items/{productId}")
    public ApiResponse<CartResponse> removeItemFromCart(@PathVariable String productId) {
        CartResponse updatedCart = cartService.removeItemFromCart(productId);
        return ApiResponse.<CartResponse>builder().data(updatedCart).build();
    }
}
