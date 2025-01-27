package com.entrevista.compra_api.infra.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.entrevista.compra_api.entities.Usuario;
import com.entrevista.compra_api.exception.TokenCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("compra-api")
                    .withSubject(usuario.getEmail()).withExpiresAt(Instant.now().plusSeconds(3600)).sign(algorithm);
        } catch (JWTCreationException e) {
            throw new TokenCreationException();
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("compra-api").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return "Invalid token";
        }
    }
}
