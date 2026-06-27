package com.example.job.payload;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
    private String fullName;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10-digit Indian mobile number"
    )
    private String phone;

    @Pattern(
            regexp = "^(https?://.*|.*\\.(jpg|jpeg|png|gif|webp))$",
            message = "Profile image must be a valid URL or image file path"
    )
    private String profileImage;
}