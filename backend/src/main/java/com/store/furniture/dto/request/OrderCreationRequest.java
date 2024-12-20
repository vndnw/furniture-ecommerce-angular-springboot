package com.store.furniture.dto.request;

import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {
    String shippingAddress;
    String phoneNumber;
    String fullName;
    List<OrderItemRequest> orderItems;
}
