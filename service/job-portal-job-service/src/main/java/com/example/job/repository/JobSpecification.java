package com.example.job.repository;

import com.example.job.domain.JobStatus;
import com.example.job.dto.request.JobSearchRequest;
import com.example.job.entity.Job;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobSpecification {

    private JobSpecification() {
    }

    public static Specification<Job> build(JobSearchRequest req) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Active Jobs
            predicates.add(cb.isTrue(root.get("isActive")));

            // Job Status (Default = OPEN)
            JobStatus status = req.getJobStatus() != null
                    ? req.getJobStatus()
                    : JobStatus.OPEN;

            predicates.add(cb.equal(root.get("jobStatus"), status));

            // Job Type
            if (req.getJobType() != null) {
                predicates.add(
                        cb.equal(root.get("jobType"), req.getJobType())
                );
            }

            // Work Mode
            if (req.getWorkMode() != null) {
                predicates.add(
                        cb.equal(root.get("workMode"), req.getWorkMode())
                );
            }

            // Experience Level
            if (req.getExperienceLevel() != null) {
                predicates.add(
                        cb.equal(root.get("experienceLevel"),
                                req.getExperienceLevel())
                );
            }

            // Company
            if (req.getCompanyId() != null && !req.getCompanyId().isBlank()) {
                predicates.add(
                        cb.equal(root.get("companyId"),
                                req.getCompanyId())
                );
            }

            // Location Search (City / State / Country)
            if (req.getLocation() != null && !req.getLocation().isBlank()) {

                String location = "%" + req.getLocation().toLowerCase() + "%";

                predicates.add(
                        cb.or(
                                cb.like(
                                        cb.lower(root.get("location").get("city")),
                                        location
                                ),
                                cb.like(
                                        cb.lower(root.get("location").get("state")),
                                        location
                                ),
                                cb.like(
                                        cb.lower(root.get("location").get("country")),
                                        location
                                )
                        )
                );
            }

            // Keyword Search
            if (req.getKeyword() != null && !req.getKeyword().isBlank()) {

                String keyword = "%" + req.getKeyword().toLowerCase() + "%";

                predicates.add(
                        cb.or(
                                cb.like(
                                        cb.lower(root.get("title")),
                                        keyword
                                ),
                                cb.like(
                                        cb.lower(root.get("description")),
                                        keyword
                                ),
                                cb.like(
                                        cb.lower(root.get("requirements")),
                                        keyword
                                )
                        )
                );
            }

            // Minimum Salary
            if (req.getMinSalary() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("salaryRange").get("maxSalary"),
                                req.getMinSalary()
                        )
                );
            }

            // Maximum Salary
            if (req.getMaxSalary() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("salaryRange").get("minSalary"),
                                req.getMaxSalary()
                        )
                );
            }

            // Minimum Openings
            if (req.getMinOpenings() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("openings"),
                                req.getMinOpenings()
                        )
                );
            }

            // Maximum Openings
            if (req.getMaxOpenings() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("openings"),
                                req.getMaxOpenings()
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}