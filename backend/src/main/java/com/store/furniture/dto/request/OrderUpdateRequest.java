package com.store.furniture.dto.request;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants(level = AccessLevel.PRIVATE)
public class OrderUpdateRequest {
    String shippingAddress;
    String phoneNumber;
    String fullName;
}
