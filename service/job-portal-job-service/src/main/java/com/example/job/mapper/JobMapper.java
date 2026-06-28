package com.example.job.mapper;

import com.example.job.dto.response.JobResponse;
import com.example.job.entity.Job;

public class JobMapper {


    public static JobResponse mapToResponse(Job job) {

        if (job == null) {
            return null;
        }

        return JobResponse.builder()
                .jobId(job.getJobId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())

                .companyId(job.getCompanyId())

//                .category(job.getCategory())
//                .skills(job.getSkills())
//                .tags(job.getTags())

                .city(job.getLocation().getCity())
                .address(job.getLocation().getAddress())
                .country(job.getLocation().getCountry())
                .state(job.getLocation().getState())
                .minSalary(job.getSalaryRange().getMinSalary())
                .maxSalary(job.getSalaryRange().getMaxSalary())

                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())

                .openings(job.getOpenings())

                .applicationDeadLine(job.getApplicationDeadLine())
                .expiredAt(job.getExpiredAt())

                .status(job.getJobStatus())

                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())


                .build();
    }
}
