package com.store.furniture.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemToCartRequest {
    @NotBlank(message = "PRODUCT_ID_MANDATORY")
    String productId;

    @NotNull(message = "QUANTITY_MANDATORY")
    @Min(value = 1, message = "QUANTITY_MINIMUM")
    int quantity;
}
