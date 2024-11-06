package com.store.furniture.dto.request;

import com.store.furniture.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdateRequest {
    OrderStatus status;
    Double totalAmount;
    String shippingAddress;
    String shippingPhoneNumber;
}
