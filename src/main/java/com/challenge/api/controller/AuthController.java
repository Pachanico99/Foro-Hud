package com.challenge.api.controller;

import com.challenge.api.infra.security.AuthRequestDTO;
import com.challenge.api.infra.security.AuthService;
import com.challenge.api.infra.security.LoginAuthDTO;
import com.challenge.api.infra.security.RegisterAuthDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthRequestDTO> login(@RequestBody @Valid LoginAuthDTO request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthRequestDTO> register(@RequestBody @Valid RegisterAuthDTO request){
        return ResponseEntity.ok(authService.register(request));
    }
}
