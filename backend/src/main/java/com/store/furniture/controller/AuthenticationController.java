package com.store.furniture.controller;

import com.nimbusds.jose.JOSEException;
import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.*;
import com.store.furniture.dto.response.AuthenticationResponse;
import com.store.furniture.dto.response.IntrospectResponse;
import com.store.furniture.service.AuthenticationService;
import jakarta.validation.Valid;
import java.text.ParseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder().data(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder().data(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/sendOtp")
    ApiResponse<Void> sendOtp(@RequestBody @Valid ForgotPasswordRequest forgotPasswordRequest) {
        authenticationService.sendOtp(forgotPasswordRequest);
        return ApiResponse.<Void>builder().build();
    }
    @PostMapping("/verifyOtp")
    ApiResponse<Void> verifyOtp(@RequestBody @Valid VerifyOtpRequest verifyOtpRequest) {
        authenticationService.verifyOtp(verifyOtpRequest);
        return ApiResponse.<Void>builder().build();
    }
}
