package com.example.job.dto.request;


import com.example.job.domain.CompanySize;
import com.example.job.domain.CompanyType;
import com.example.job.domain.IndustryType;
import com.example.job.dto.response.SocialLinkResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 150, message = "Company name must be between 2 and 150 characters")
    private String name;

    @Size(max = 100, message = "Slug cannot exceed 100 characters")
    @Pattern(
            regexp = "^[a-z0-9-]+$",
            message = "Slug can contain only lowercase letters, numbers and hyphens"
    )
    private String slug;

    @NotBlank(message = "Company email is required")
    @Email(message = "Please provide a valid company email")
    @Size(max = 150, message = "Company email cannot exceed 150 characters")
    private String companyEmail;

    @Size(max = 200, message = "Tagline cannot exceed 200 characters")
    private String tagline;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @Pattern(
            regexp = "^(https?://.*)?$",
            message = "Logo URL must be a valid URL"
    )
    private String logoUrl;

    @Pattern(
            regexp = "^(https?://.*)?$",
            message = "Cover image URL must be a valid URL"
    )
    private String coverImageUrl;

    @Pattern(
            regexp = "^(https?://.*)?$",
            message = "Website must be a valid URL"
    )
    private String website;

    @Min(value = 1800, message = "Founded year is invalid")
    @Max(value = 2100, message = "Founded year is invalid")
    private Integer foundedYear;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Industry type is required")
    private IndustryType industryType;

    @NotBlank(message = "Registration number is required")
    @Size(max = 100)
    private String registerNumber;

    @Valid
    @Builder.Default
    private List<SocialLinkResponse> socialLinks = new ArrayList<>();
}