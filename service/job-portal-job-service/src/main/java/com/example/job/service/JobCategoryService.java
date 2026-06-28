package com.example.job.service;

import com.example.job.dto.request.JobCategoryRequest;
import com.example.job.dto.response.JobCategoryResponse;
import com.example.job.entity.JobCategory;

import java.util.List;

public interface JobCategoryService {

    JobCategoryResponse createCategory(JobCategoryRequest req) throws Exception;

    List<JobCategoryResponse> getAllCategories();

    JobCategoryResponse getCategoryById(String id) throws Exception;

    JobCategoryResponse updateCategory(String id, JobCategoryRequest req) throws Exception;

    void deleteCategory(String id) throws Exception;

    JobCategory getCategoryEntityById(String id) throws Exception;
}