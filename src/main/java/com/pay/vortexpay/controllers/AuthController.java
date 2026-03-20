package com.pay.vortexpay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.request.LoginRequestDTO;
import com.pay.vortexpay.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and issue session cookie", description = "Takes user credentials, validates them against the database, and returns a secure HTTP-Only cookie containing the authentication token if successful.")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO dto, HttpServletResponse response) {
        Cookie authCookie = authService.login(dto);
        response.addCookie(authCookie);

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/logout")
    @Operation(summary = "Terminate user session", description = "Clears the authentication state by overwriting the existing session cookie with an expired one, effectively logging the user out of the system.")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie clearCookie = authService.logout();
        response.addCookie(clearCookie);
        
        return ResponseEntity.status(200).build();
    }
}
