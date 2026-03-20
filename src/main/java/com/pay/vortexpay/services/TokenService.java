package com.pay.vortexpay.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pay.vortexpay.entities.User;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
        .withIssuer("vortexpay-api")
        .withSubject(user.getEmail())
        .withClaim("id", user.getId().toString())
        .withExpiresAt(genExpirationDate())
        .sign(algorithm);
    }

    public String validateToken(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
            .withIssuer("vortexpay-api")
            .build()
            .verify(token)
            .getSubject();

        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Instant genExpirationDate() {
        //Expires in 2 hours
       return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); 
    }
}
