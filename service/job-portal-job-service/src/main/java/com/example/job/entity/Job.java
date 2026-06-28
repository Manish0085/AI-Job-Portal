package com.example.job.entity;


import com.example.job.domain.ExperienceLevel;
import com.example.job.domain.JobStatus;
import com.example.job.domain.JobType;
import com.example.job.domain.WorkMode;
import com.example.job.entity.embedable.JobLocation;
import com.example.job.entity.embedable.SalaryRange;
import com.example.job.modal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "job")
public class Job extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String jobId;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String requirements;

    private String responsibilities;

    private String benefits;

    @Column(nullable = false)
    private String companyId;

    private String employerId;

//    private JobCategory jobCategory;
//
//    private Set<JobSkills> skills;
//    private Set<JobTag> tags;


    @Embedded
    private JobLocation location;


    @Embedded
    private SalaryRange salaryRange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkMode workMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus jobStatus = JobStatus.DRAFT;

    private Integer openings = 1;

    private LocalDateTime applicationDeadLine;

    private LocalDateTime expiredAt;

    private Boolean isActive=true;


    private LocalDateTime publishedAt;

    private LocalDateTime closedAt;

}
