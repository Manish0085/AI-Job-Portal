package com.example.job.controller;


import com.example.job.payload.AuthResponse;
import com.example.job.payload.LoginRequest;
import com.example.job.payload.SignupRequest;
import com.example.job.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponse> signup (
            @RequestBody SignupRequest request
            ) throws Exception{
        return ResponseEntity.ok(authService.signup(request));

    }

    @PostMapping
    public ResponseEntity<AuthResponse> login (
            @RequestBody LoginRequest request
    ) throws Exception{
        return ResponseEntity.ok(authService.login(request));

    }
 }
