package com.example.job.service;

import com.example.job.dto.request.JobRequest;
import com.example.job.dto.request.JobSearchRequest;
import com.example.job.dto.response.JobResponse;

import java.util.List;

public interface JobService {

    JobResponse createJob(String companyId, JobRequest request);

    JobResponse getJobById(String jobId) throws Exception;

    List<JobResponse> getJobs(JobSearchRequest request);

    List<JobResponse> getJobsByCompany(String companyId);

    JobResponse updateJob(String jobId, String companyId, JobRequest request) throws Exception;

    JobResponse publishJob(String jobId, String companyId) throws Exception;

    JobResponse closeJob(String jobId, String companyId) throws Exception;

    void deleteJob(String jobId, String companyId) throws Exception;

    List<JobResponse> getAllJobsAdmin();
}