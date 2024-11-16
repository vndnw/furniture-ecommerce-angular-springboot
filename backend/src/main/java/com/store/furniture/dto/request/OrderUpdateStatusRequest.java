package com.store.furniture.dto.request;

import com.store.furniture.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants(level = AccessLevel.PRIVATE)
public class OrderUpdateStatusRequest {
    OrderStatus status;
}
