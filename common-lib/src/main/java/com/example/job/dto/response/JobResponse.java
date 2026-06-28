package com.example.job.dto.response;

import com.example.job.domain.ExperienceLevel;
import com.example.job.domain.JobStatus;
import com.example.job.domain.JobType;
import com.example.job.domain.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobResponse {

    private String jobId;

    private String title;

    private String description;

    private String requirements;

    private String responsibilities;

    private String benefits;

    private String companyId;

    // Location
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    // Salary
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    // Classification
    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus status;

    // Posting Details
    private Integer openings;
    private LocalDateTime applicationDeadLine;
    private LocalDateTime expiredAt;
    private Boolean active;
    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;

    // Audit
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}