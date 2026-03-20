package com.pay.vortexpay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.vortexpay.dtos.request.LoginRequestDTO;
import com.pay.vortexpay.services.AuthService;

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
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO dto, HttpServletResponse response) {
        Cookie authCookie = authService.login(dto);
        response.addCookie(authCookie);

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie clearCookie = authService.logout();
        response.addCookie(clearCookie);
        
        return ResponseEntity.status(200).build();
    }
}
