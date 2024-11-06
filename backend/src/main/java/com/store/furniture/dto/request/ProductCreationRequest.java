package com.store.furniture.dto.request;

import com.store.furniture.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    String name;
    String description;
    double price;
    String image;
    Long categoryId;
}
