package com.example.job.service.impl;

import com.example.job.dto.request.JobCategoryRequest;
import com.example.job.dto.response.JobCategoryResponse;
import com.example.job.entity.JobCategory;
import com.example.job.mapper.JobCategoryMapper;
import com.example.job.repository.JobCategoryRepository;
import com.example.job.service.JobCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCategoryServiceImpl implements JobCategoryService {

    private JobCategoryRepository jobCategoryRepository;

    @Override
    @Transactional
    public JobCategoryResponse createCategory(JobCategoryRequest req) throws Exception {

        // Name validation
        String name = req.getName().trim();

        if (jobCategoryRepository.existsByName(name)) {
            throw new IllegalArgumentException(
                    "Job category already exists with name: " + name
            );
        }

        // Generate slug
        String slug = generateUniqueSlug(req.getName());

        if (jobCategoryRepository.existsBySlug(slug)) {
            throw new IllegalArgumentException(
                    "Slug already exists: " + slug
            );
        }

        JobCategory parent = null;

        // Parent Category
        if (req.getParentId() != null) {

            parent = jobCategoryRepository.findById(req.getParentId())
                    .orElseThrow(() ->
                            new Exception(
                                    "Job Category not found with id : "
                                            + req.getParentId()));
        }

        JobCategory category = JobCategory.builder()
                .name(name)
                .slug(slug)
                .description(req.getDescription())
                .iconUrl(req.getIconUrl())
                .parent(parent)
                .build();

        JobCategory savedCategory =
                jobCategoryRepository.save(category);

        return JobCategoryMapper.toResponse(savedCategory);
    }

    private String generateUniqueSlug(String name) {

        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        if (!jobCategoryRepository.existsBySlug(base)) {
            return base;
        }

        int counter = 1;

        while (jobCategoryRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }

        return base + "-" + counter;
    }

    @Override
    public List<JobCategoryResponse> getAllCategories() {

        return jobCategoryRepository.findAll()
                .stream()
                .map(JobCategoryMapper::toResponse)
                .toList();
    }

    @Override
    public JobCategoryResponse getCategoryById(String id) throws Exception {
        JobCategory jobCategory = jobCategoryRepository.findById(id).orElseThrow(
                () -> new Exception("Job Category not found")
        );

        return JobCategoryMapper.toResponse(jobCategory);
    }

    @Override
    public JobCategoryResponse updateCategory(String id, JobCategoryRequest req) throws Exception {

        JobCategory category = getCategoryEntityById(id);

        // Check duplicate name
        if (!category.getName().equals(req.getName())
                && jobCategoryRepository.existsByName(req.getName())) {

            throw new Exception("Category name already exists, choose a different name");
        }

        JobCategory parent = null;

        if (req.getParentId() != null) {

            // Cannot be its own parent
            if (req.getParentId().equals(id)) {
                throw new Exception("A category cannot be its own parent");
            }

            parent = getCategoryEntityById(req.getParentId());
        }

        category.setName(req.getName());
        category.setSlug(generateUniqueSlug(req.getName()));
        category.setDescription(req.getDescription());
        category.setIconUrl(req.getIconUrl());
        category.setParent(parent);

        JobCategory updatedCategory = jobCategoryRepository.save(category);

        return JobCategoryMapper.toResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(String id) throws Exception {
        JobCategory jobCategory = jobCategoryRepository.findById(id).orElseThrow(
                () -> new Exception("Job Category not found")
        );
        jobCategoryRepository.delete(jobCategory);

    }

    @Override
    public JobCategory getCategoryEntityById(String id) throws Exception {

        return jobCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Job category not found with id: " + id));
    }
}