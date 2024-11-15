package com.store.furniture.dto.response;

import com.store.furniture.entity.User;
import com.store.furniture.enums.OrderStatus;
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
public class OrderResponse {
     String id;
     String userId;
     double totalAmount;
     OrderStatus status;
     Instant createdAt;
     Instant updatedAt;
    List<OrderItemResponse> orderItems;
}
