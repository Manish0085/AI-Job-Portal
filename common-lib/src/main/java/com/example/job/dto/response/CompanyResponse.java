package com.example.job.dto.response;

import com.example.job.domain.CompanySize;
import com.example.job.domain.CompanyStatus;
import com.example.job.domain.CompanyType;
import com.example.job.domain.IndustryType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponse {

    private String id;

    private String name;

    private String slug;

    private String email;

    private String tagline;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    private String website;

    private Integer foundedYear;

    private CompanySize companySize;

    private CompanyType companyType;

    private IndustryType industryType;

    private CompanyStatus companyStatus;

    private String registerNumber;

    private String ownerId;

    private List<SocialLinkResponse> socialLinks;

    private Boolean active;

    // Audit fields inherited from BaseEntity
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}