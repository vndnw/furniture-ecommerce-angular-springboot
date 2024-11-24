package com.store.furniture.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyOtpRequest {
    @NotBlank(message = "EMAIL_MANDATORY")
    @Email(message = "EMAIL_INVALID")
    String email;

    @NotBlank(message = "OTP_MANDATORY")
    String otp;

    @NotBlank(message = "NEW_PASSWORD_MANDATORY")
    @Size(min = 5, max = 100, message = "PASSWORD_SIZE")
    String newPassword;
}
