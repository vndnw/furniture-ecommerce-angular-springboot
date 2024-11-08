package com.store.furniture.service;

import com.store.furniture.dto.request.CartCreationRequest;
import com.store.furniture.dto.request.ProductCreationRequest;
import com.store.furniture.dto.request.ProductUpdateRequest;
import com.store.furniture.dto.response.CartResponse;
import com.store.furniture.dto.response.ProductResponse;
import com.store.furniture.entity.Admin;
import com.store.furniture.entity.Cart;
import com.store.furniture.entity.Product;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.CartMapper;
import com.store.furniture.mapper.ProductMapper;
import com.store.furniture.repository.CartRepository;
import com.store.furniture.repository.CategoryRepository;
import com.store.furniture.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepository cartRepository;
    CartMapper cartMapper;

    public CartResponse createCart(CartCreationRequest request) {
        var cart = cartMapper.tocCart(request);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    public CartResponse getCartById(String id) {
        var cart = cartRepository.findById(id)
                .orElseThrow(
                        () -> new AppException(ErrorCode.USERNAME_EXISTS)
                );
        return cartMapper.toCartResponse(cart);
    }

    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }

}