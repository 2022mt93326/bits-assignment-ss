package com.bits.auth.service.service;

import com.bits.auth.service.model.AuthRequest;
import com.bits.auth.service.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    public AuthResponse register(AuthRequest authRequest) {
        String accessToken = jwtService.generate(authRequest.getEmail(), authRequest.getRole(), "ACCESS");
        String refreshToken = jwtService.generate(authRequest.getEmail(), authRequest.getRole(),"REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}