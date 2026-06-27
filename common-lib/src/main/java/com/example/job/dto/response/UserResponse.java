package com.example.job.dto.response;


import com.example.job.domain.AuthProvider;
import com.example.job.domain.UserRole;
import com.example.job.domain.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private String id;

    private String fullName;

    private String email;

    private String phone;

    private String imageUrl;

    private AuthProvider authProvider;

    private UserRole role;

    private UserStatus status;

    private LocalDateTime lastLogin;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}