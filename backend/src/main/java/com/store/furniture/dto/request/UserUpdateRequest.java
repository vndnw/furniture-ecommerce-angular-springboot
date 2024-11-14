package com.store.furniture.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @NotBlank(message = "NAME_MANDATORY")
    @Size(min = 2, max = 50, message = "NAME_SIZE")
    String name;

    @NotBlank(message = "EMAIL_MANDATORY")
    @Email(message = "EMAIL_INVALID")
    String email;

    @NotBlank(message = "ADDRESS_MANDATORY")
    @Size(min = 5, max = 100, message = "ADDRESS_SIZE")
    String address;

    @NotBlank(message = "PHONE_MANDATORY")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "PHONE_INVALID")
    String phone;
}
