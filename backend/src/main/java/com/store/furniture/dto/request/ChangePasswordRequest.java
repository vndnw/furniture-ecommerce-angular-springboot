package com.store.furniture.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {
    @NotBlank(message = "CURRENT_PASSWORD_MANDATORY")
    @Size(min = 5, max = 20, message = "PASSWORD_SIZE")
    String currentPassword;

    @NotBlank(message = "NEW_PASSWORD_MANDATORY")
    @Size(min = 5, max = 20, message = "PASSWORD_SIZE")
    String newPassword;
}
