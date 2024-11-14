package com.store.furniture.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {
    @NotBlank(message = "NAME_MANDATORY")
    String name;

    @NotBlank(message = "DESCRIPTION_MANDATORY")
    String description;
}
