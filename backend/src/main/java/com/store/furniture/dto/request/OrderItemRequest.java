package com.store.furniture.dto.request;

import com.store.furniture.entity.OrderItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequest {
    String productId;
    int quantity;
}
