package com.store.furniture.dto.response;

import com.store.furniture.enums.OrderStatus;
import java.time.Instant;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String userId;
    String shippingAddress;
    String phoneNumber;
    String fullName;
    double totalAmount;
    OrderStatus status;
    Instant createdAt;
    Instant updatedAt;
    List<OrderItemResponse> orderItems;
}
