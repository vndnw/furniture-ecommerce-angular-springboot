package com.store.furniture.service;

import com.store.furniture.dto.request.ItemToCartRequest;
import com.store.furniture.dto.response.CartResponse;
import com.store.furniture.entity.*;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.CartMapper;
import com.store.furniture.repository.CartRepository;
import com.store.furniture.repository.ProductRepository;
import com.store.furniture.repository.UserRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {

    CartRepository cartRepository;
    ProductRepository productRepository;
    CartMapper cartMapper;
    UserRepository userRepository;

    public CartResponse getCartByCustomerId() {
        String authenticatedUsername =
                SecurityContextHolder.getContext().getAuthentication().getName();

        User authenticatedUser = userRepository
                .findByUsername(authenticatedUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        String customerId = authenticatedUser.getId();
        Cart cart =
                cartRepository.findByUserId(customerId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        return cartMapper.toResponse(cart);
    }

    public CartResponse addItemToCart(ItemToCartRequest itemToCartRequest) {
        String authenticatedUsername =
                SecurityContextHolder.getContext().getAuthentication().getName();

        User authenticatedUser = userRepository
                .findByUsername(authenticatedUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        String customerId = authenticatedUser.getId();

        Cart cart = cartRepository.findByUserId(customerId).orElseGet(() -> createNewCart(customerId));

        Product product = productRepository
                .findById(itemToCartRequest.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(itemToCartRequest.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + itemToCartRequest.getQuantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(itemToCartRequest.getQuantity());
            newItem.setPrice(product.getPrice());
            cart.getCartItems().add(newItem);
        }

        return cartMapper.toResponse(cartRepository.save(cart));
    }

    public CartResponse updateCartItem(ItemToCartRequest itemToCartRequest) {
        String authenticatedUsername =
                SecurityContextHolder.getContext().getAuthentication().getName();

        User authenticatedUser = userRepository
                .findByUsername(authenticatedUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        String customerId = authenticatedUser.getId();

        Cart cart =
                cartRepository.findByUserId(customerId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        Optional<CartItem> cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(itemToCartRequest.getProductId()))
                .findFirst();

        if (cartItem.isPresent()) {
            CartItem item = cartItem.get();
            item.setQuantity(itemToCartRequest.getQuantity());
        } else {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        return cartMapper.toResponse(cartRepository.save(cart));
    }

    public CartResponse removeItemFromCart(String productId) {
        String authenticatedUsername =
                SecurityContextHolder.getContext().getAuthentication().getName();

        User authenticatedUser = userRepository
                .findByUsername(authenticatedUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        String customerId = authenticatedUser.getId();
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
