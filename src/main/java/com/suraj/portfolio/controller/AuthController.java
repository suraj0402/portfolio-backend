package com.suraj.portfolio.controller;

import org.springframework.web.bind.annotation.*;

import com.suraj.portfolio.dto.LoginRequest;
import com.suraj.portfolio.dto.LoginResponse;
import com.suraj.portfolio.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        if (
                "admin".equals(request.getUsername())
                        &&
                "admin123".equals(request.getPassword())
        ) {

            String token =
                    jwtService.generateToken(
                            request.getUsername()
                    );

            return new LoginResponse(token);
        }

        throw new RuntimeException(
                "Invalid credentials"
        );
    }
}