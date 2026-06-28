package com.example.job.dto.request;

import com.example.job.domain.ExperienceLevel;
import com.example.job.domain.JobStatus;
import com.example.job.domain.JobType;
import com.example.job.domain.WorkMode;
import com.example.job.dto.response.JobLocation;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSearchRequest {

    private String keyword;

    private String companyId;

    /**
     * Matches city, state or country.
     */
    private String location;

    /**
     * Job's max salary >= minSalary
     */
    private BigDecimal minSalary;

    /**
     * Job's min salary <= maxSalary
     */
    private BigDecimal maxSalary;

    private JobType jobType;

    private WorkMode workMode;

    private ExperienceLevel experienceLevel;

    /**
     * Defaults to OPEN if null.
     */
    private JobStatus jobStatus;

    private Integer minOpenings;

    private Integer maxOpenings;
}