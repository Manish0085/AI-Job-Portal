package com.example.job.dto.request;

import com.example.job.domain.ExperienceLevel;
import com.example.job.domain.JobType;
import com.example.job.domain.WorkMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Requirements are required")
    private String requirements;

    private String responsibilities;

    private String benefits;

    @NotBlank(message = "Company Id is required")
    private String companyId;

    // Location
    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Zip Code is required")
    private String zipCode;

    // Salary
    @NotNull(message = "Minimum salary is required")
    private BigDecimal minSalary;

    @NotNull(message = "Maximum salary is required")
    private BigDecimal maxSalary;

    // Classification
    @NotNull(message = "Job type is required")
    private JobType jobType;

    @NotNull(message = "Work mode is required")
    private WorkMode workMode;

    @NotNull(message = "Experience level is required")
    private ExperienceLevel experienceLevel;

    // Posting Details
    private Integer openings;

    private LocalDateTime applicationDeadLine;

    private LocalDateTime expiredAt;
}