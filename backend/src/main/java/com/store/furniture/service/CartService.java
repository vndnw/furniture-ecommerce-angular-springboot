package com.store.furniture.service;

import com.store.furniture.dto.response.CartResponse;
import com.store.furniture.entity.*;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.CartMapper;
import com.store.furniture.repository.CartRepository;
import com.store.furniture.repository.ProductRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {

    CartRepository cartRepository;
    ProductRepository productRepository;
    CartMapper cartMapper;

    public CartResponse getCartByCustomerId(String customerId) {
        Cart cart =
                cartRepository.findByUserId(customerId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        return cartMapper.toResponse(cart);
    }

    public CartResponse addItemToCart(String customerId, String productId, int quantity) {
        Cart cart = cartRepository.findByUserId(customerId).orElseGet(() -> createNewCart(customerId));

        Product product =
                productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setPrice(product.getPrice());
            cart.getCartItems().add(newItem);
        }

        return cartMapper.toResponse(cartRepository.save(cart));
    }

    public CartResponse updateCartItem(String customerId, String productId, int quantity) {
        Cart cart =
                cartRepository.findByUserId(customerId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        Optional<CartItem> cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (cartItem.isPresent()) {
            CartItem item = cartItem.get();
            item.setQuantity(quantity);
        } else {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        return cartMapper.toResponse(cartRepository.save(cart));
    }

    public CartResponse removeItemFromCart(String customerId, String productId) {
        Cart cart =
                cartRepository.findByUserId(customerId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        cart.getCartItems().remove(cartItem);
        return cartMapper.toResponse(cartRepository.save(cart));
    }

    private Cart createNewCart(String customerId) {
        Cart cart = new Cart();
        User user = new User();
        user.setId(customerId);
        cart.setUser(user);
        return cartRepository.save(cart);
    }
}
