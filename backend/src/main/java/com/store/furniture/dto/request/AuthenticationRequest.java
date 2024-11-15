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
public class AuthenticationRequest {
    @NotBlank(message = "USERNAME_MANDATORY")
    @Size(min = 2, max = 50, message = "USERNAME_SIZE")
    String username;

    @NotBlank(message = "PASSWORD_MANDATORY")
    @Size(min = 5, max = 100, message = "PASSWORD_SIZE")
    String password;
}
