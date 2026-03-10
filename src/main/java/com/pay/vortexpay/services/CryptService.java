package com.pay.vortexpay.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pay.vortexpay.entities.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CryptService {
    private final PasswordEncoder passwordEncoder;

    public void encryptPassword(User user, String dtoPassword) {
        if(!dtoPassword.isBlank()) {
            String safePassword = passwordEncoder.encode(dtoPassword);
            user.setPassword(safePassword);
        }
    }
}
