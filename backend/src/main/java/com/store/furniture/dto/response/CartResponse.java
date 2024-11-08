package com.store.furniture.dto.response;

import com.store.furniture.entity.CartItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    String id;
    String customerId;
    List<CartItem> items;
    Instant createdAt;
    Instant updatedAt;
}