package com.store.furniture.controller;

import com.nimbusds.jose.JOSEException;
import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.AuthenticationRequest;
import com.store.furniture.dto.request.IntrospectRequest;
import com.store.furniture.dto.request.LogoutRequest;
import com.store.furniture.dto.response.AuthenticationResponse;
import com.store.furniture.dto.response.IntrospectResponse;
import com.store.furniture.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(result)
                .build();

    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var result = authenticationService.introspect(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();

    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.Logout(logoutRequest);
        return ApiResponse.<Void>builder()
                .build();
    }


}
