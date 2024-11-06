package com.store.furniture.dto.response;

import com.store.furniture.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String customerId;
    OrderStatus status;
    Double totalAmount;
    String shippingAddress;
    String shippingPhoneNumber;
    Instant createdAt;
    Instant updatedAt;
}
