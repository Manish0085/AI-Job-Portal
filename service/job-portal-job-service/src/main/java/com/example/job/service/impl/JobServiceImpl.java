package com.example.job.service.impl;

import com.example.job.domain.JobStatus;
import com.example.job.dto.request.JobRequest;
import com.example.job.dto.request.JobSearchRequest;
import com.example.job.dto.response.JobResponse;
import com.example.job.entity.Job;
import com.example.job.entity.embedable.JobLocation;
import com.example.job.entity.embedable.SalaryRange;
import com.example.job.mapper.JobMapper;
import com.example.job.repository.JobRepository;
import com.example.job.repository.JobSpecification;
import com.example.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.job.mapper.JobMapper.mapToResponse;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;


    @Override
    public JobResponse createJob(String employerId, JobRequest req) {

        // TODO: Fetch company using employerId
        String companyId = "COMPANY_ID";

        Job job = Job.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .requirements(req.getRequirements())
                .responsibilities(req.getResponsibilities())
                .benefits(req.getBenefits())
                .companyId(companyId)

//                .category(req.getCategory())
//                .skills(req.getSkills())
//                .tags(req.getTags())

                .employerId(employerId)
                .location(buildLocation(req))
                .salaryRange(buildSalary(req))

                .jobType(req.getJobType())
                .workMode(req.getWorkMode())
                .experienceLevel(req.getExperienceLevel())

                .openings(req.getOpenings())
                .applicationDeadLine(req.getApplicationDeadLine())
                .expiredAt(req.getExpiredAt())

                .jobStatus(JobStatus.OPEN)

                .build();

        Job savedJob = jobRepository.save(job);

        return mapToResponse(savedJob);
    }

    private JobLocation buildLocation(JobRequest req) {
        return JobLocation.builder()
                .country(req.getCountry())
                .state(req.getState())
                .city(req.getCity())
                .address(req.getAddress())
                .pinCode(req.getZipCode())
                .build();
    }

    private SalaryRange buildSalary(JobRequest req) {
        return SalaryRange.builder()
                .maxSalary(req.getMaxSalary())
                .minSalary(req.getMinSalary())
                .build();
    }

    @Override
    public JobResponse getJobById(String jobId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found")
        );

        return mapToResponse(job);
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepository.findAll(JobSpecification.build(request));
        return jobs.stream().map(
                job -> mapToResponse(job)
        ).toList();
    }

    @Override
    public List<JobResponse> getJobsByCompany(String companyId) {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        return jobs.stream().map(
                job -> mapToResponse(job)
        ).toList();
    }

    @Override
    public JobResponse updateJob(String jobId, String employerId, JobRequest req) throws Exception {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new Exception("Job not found"));

        assertEmployer(job, employerId);

        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setRequirements(req.getRequirements());
        job.setResponsibilities(req.getResponsibilities());
        job.setBenefits(req.getBenefits());

        // todo : category not implemented yet
//    job.setCategory(category);
//    job.setSkills(skills);
//    job.setTags(tags);

        job.setLocation(buildLocation(req));
        job.setSalaryRange(buildSalary(req));

        job.setJobType(req.getJobType());
        job.setWorkMode(req.getWorkMode());
        job.setExperienceLevel(req.getExperienceLevel());

        job.setOpenings(
                req.getOpenings() != null
                        ? req.getOpenings()
                        : job.getOpenings()
        );

        job.setApplicationDeadLine(req.getApplicationDeadLine());
        job.setExpiredAt(req.getExpiredAt());

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);
    }

    @Override
    public JobResponse publishJob(String jobId, String employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found")
        );

        assertEmployer(job, employerId);

        if (job.getJobStatus() == JobStatus.CLOSED || job.getJobStatus() == JobStatus.EXPIRED) {
            throw new Exception("Job is Expired");
        }
        job.setJobStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setIsActive(true);
        return mapToResponse(job);
    }

    private void assertEmployer(Job job, String employerId) throws Exception {
        if (!job.getEmployerId().equals(employerId)) {
            throw new Exception("You are not the employer who posted the job");
        }
    }

    @Override
    public JobResponse closeJob(String jobId, String employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found")
        );

        assertEmployer(job, employerId);

        job.setJobStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setIsActive(false);
        return mapToResponse(job);
    }

    @Override
    public void deleteJob(String jobId, String employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found")
        );

        assertEmployer(job, employerId);
        jobRepository.delete(job);
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return jobRepository.findAll().stream().map(
                JobMapper::mapToResponse
        ).toList();
    }
}
