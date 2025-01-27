package com.entrevista.compra_api.controller;

import com.entrevista.compra_api.entities.Usuario;
import com.entrevista.compra_api.infra.security.service.TokenService;
import com.entrevista.compra_api.model.AuthenticationRequestBody;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    public AuthenticationController(@Lazy AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated AuthenticationRequestBody requestBody) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestBody.email(), requestBody.password());
        Authentication auth = this.authenticationManager.authenticate(token);
        return ResponseEntity.ok().body(tokenService.generateToken((Usuario) auth.getPrincipal()));
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
