package com.store.furniture.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {
    String customerId;
    Double totalAmount;
    String shippingAddress;
    String shippingPhoneNumber;
}
