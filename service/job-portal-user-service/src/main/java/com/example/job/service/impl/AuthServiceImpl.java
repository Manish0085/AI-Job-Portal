package com.example.job.service.impl;

import com.example.job.domain.UserRole;
import com.example.job.domain.UserStatus;
import com.example.job.mapper.UserMapper;
import com.example.job.modal.User;
import com.example.job.payload.AuthResponse;
import com.example.job.payload.LoginRequest;
import com.example.job.payload.SignupRequest;
import com.example.job.repository.UserRepository;
import com.example.job.security.CustomUSerDetailsService;
import com.example.job.security.JwtProvider;
import com.example.job.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;
    private CustomUSerDetailsService customUSerDetailsService;


    @Override
    public AuthResponse signup(SignupRequest request) throws Exception{
        if (userRepository.existsByEmail(request.getEmail())){
            throw new Exception("Email Already Registered " + request.getEmail());
        }

        if (request.getRole() == UserRole.ROLE_ADMIN){
            throw new Exception("You Cannot Register as the Admin" + request.getEmail());
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(request.getRole())
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication, user.getId());

        AuthResponse authResponse = AuthResponse.builder()
                .jwt(token)
                .title("Welcome " + savedUser.getFullName())
                .message("Registered Successfully")
                .user(UserMapper.toDTO(savedUser))
                .build();

        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest request) throws Exception {
        Authentication authentication = authentication(
                request.getEmail(), request.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(request.getEmail());

        String token = jwtProvider.generateToken(authentication, user.getId());
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse = AuthResponse.builder()
                .jwt(token)
                .title("Welcome back" + user.getFullName())
                .message("Logged in Successfully")
                .user(UserMapper.toDTO(user))
                .build();

        return authResponse;
    }

    private Authentication authentication(String email,String password) throws Exception {
        UserDetails userDetails = customUSerDetailsService.loadUserByUsername(email);

        if (userDetails == null)
            throw new UsernameNotFoundException("User not found " + email);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exception("Invalid Credentials");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
    }

}
