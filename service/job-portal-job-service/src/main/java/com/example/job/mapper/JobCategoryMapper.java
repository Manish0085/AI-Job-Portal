package com.example.job.mapper;

import com.example.job.dto.request.JobCategoryRequest;
import com.example.job.dto.response.JobCategoryResponse;
import com.example.job.entity.JobCategory;
import org.springframework.stereotype.Component;

@Component
public class JobCategoryMapper {

    public static JobCategory toEntity(JobCategoryRequest request) {

        if (request == null) {
            return null;
        }

        return JobCategory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .iconUrl(request.getIconUrl())
                .build();
    }

    public static JobCategoryResponse toResponse(JobCategory category) {

        if (category == null) {
            return null;
        }

        return JobCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .iconUrl(category.getIconUrl())
                .parentId(
                        category.getParent() != null
                                ? category.getParent().getId()
                                : null
                )
                .createdAt(category.getCreatedAt())
//                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public void updateEntity(JobCategory category, JobCategoryRequest request) {

        if (request == null || category == null) {
            return;
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setIconUrl(request.getIconUrl());
    }
}