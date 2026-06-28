package com.example.job.repository;

import com.example.job.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCategoryRepository extends JpaRepository<JobCategory, String> {

    boolean existsByName(String name);

    boolean existsBySlug(String slug);
}