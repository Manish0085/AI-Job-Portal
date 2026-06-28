package com.example.job.controller;

import com.example.job.dto.request.JobRequest;
import com.example.job.dto.request.JobSearchRequest;
import com.example.job.dto.response.JobResponse;
import com.example.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    /**
     * Create Job
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobResponse createJob(
            @RequestHeader("X-User-Id") String employerId,
            @Valid @RequestBody JobRequest request) {

        return jobService.createJob(employerId, request);
    }

    /**
     * Get Job By Id
     */
    @GetMapping("/{jobId}")
    public JobResponse getJobById(
            @PathVariable String jobId) throws Exception {

        return jobService.getJobById(jobId);
    }

    /**
     * Search Jobs
     */
    @PostMapping("/search")
    public List<JobResponse> searchJobs(
            @RequestBody JobSearchRequest request) {

        return jobService.getJobs(request);
    }

    /**
     * Get Jobs By Company
     */
    @GetMapping("/company/{companyId}")
    public List<JobResponse> getJobsByCompany(
            @PathVariable String companyId) {

        return jobService.getJobsByCompany(companyId);
    }

    /**
     * Update Job
     */
    @PutMapping("/{jobId}")
    public JobResponse updateJob(
            @PathVariable String jobId,
            @RequestHeader("X-Employer-Id") String employerId,
            @Valid @RequestBody JobRequest request) throws Exception {

        return jobService.updateJob(jobId, employerId, request);
    }

    /**
     * Publish Job
     */
    @PatchMapping("/{jobId}/publish")
    public JobResponse publishJob(
            @PathVariable String jobId,
            @RequestHeader("X-User-Id") String employerId) throws Exception {

        return jobService.publishJob(jobId, employerId);
    }

    /**
     * Close Job
     */
    @PatchMapping("/{jobId}/close")
    public JobResponse closeJob(
            @PathVariable String jobId,
            @RequestHeader("X-User-Id") String employerId) throws Exception {

        return jobService.closeJob(jobId, employerId);
    }

    /**
     * Delete Job
     */
    @DeleteMapping("/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(
            @PathVariable String jobId,
            @RequestHeader("X-User-Id") String employerId) throws Exception {

        jobService.deleteJob(jobId, employerId);
    }

    /**
     * Admin - Get All Jobs
     */
    @GetMapping("/admin")
    public List<JobResponse> getAllJobsAdmin() {

        return jobService.getAllJobsAdmin();
    }

}