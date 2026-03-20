package com.pay.vortexpay.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pay.vortexpay.dtos.request.LoginRequestDTO;
import com.pay.vortexpay.entities.User;
import com.pay.vortexpay.exceptions.InvalidPasswordException;
import com.pay.vortexpay.exceptions.UserNotFoundException;
import com.pay.vortexpay.repositories.UserRepository;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional
    public Cookie login(LoginRequestDTO dto) {
        User user = userRepository.findUserByEmail(dto.email()).orElseThrow(() -> new UserNotFoundException("User with email "+dto.email()+" does not exists!"));
        if(!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password!");
        }

        String token = tokenService.generateToken(user);

        Cookie cookie = new Cookie("vortexpay_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(7200); // Expires in 2 hours

        return cookie;
    }

    public Cookie logout() {
        Cookie cookie = new Cookie("vortexpay_token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        
        return cookie;
    }
}
