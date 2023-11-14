package com.bits.auth.service.controller;

import com.bits.auth.service.model.AuthRequest;
import com.bits.auth.service.model.AuthResponse;
import com.bits.auth.service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/token")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authService.register(authRequest));
    }
}