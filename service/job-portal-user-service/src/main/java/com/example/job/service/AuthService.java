package com.example.job.service;


import com.example.job.payload.AuthResponse;
import com.example.job.payload.LoginRequest;
import com.example.job.payload.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request) throws Exception;
    AuthResponse login(LoginRequest request) throws Exception;
}
