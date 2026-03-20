package com.pay.vortexpay.configs;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pay.vortexpay.exceptions.SecurityErrorException;
import com.pay.vortexpay.services.TokenService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
        try {

            String token = recoverToken(request);
            
            if(token != null) {
                String login = tokenService.validateToken(token);
                
                if(!login.isEmpty()) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(login, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(auth);;
                }
            }
            
            filterChain.doFilter(request, response);
        } catch (SecurityErrorException ex) {
            System.err.println("Error at security: "+ ex.getMessage());
        }   
    }

    private String recoverToken(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("vortexpay_token")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
