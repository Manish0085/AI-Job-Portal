package com.example.job.repository;

import com.example.job.dto.response.JobResponse;
import com.example.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> , JpaSpecificationExecutor<Job> {
    List<Job> findByCompanyId(String companyId);
}
