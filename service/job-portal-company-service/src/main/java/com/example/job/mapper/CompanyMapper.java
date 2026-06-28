package com.example.job.mapper;

import com.example.job.dto.request.CompanyRequest;
import com.example.job.dto.response.CompanyResponse;
import com.example.job.modal.Company;

public final class CompanyMapper {

    /**
     * Entity -> DTO
     */
    public static CompanyResponse toDTO(Company company) {

        if (company == null) {
            return null;
        }

        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .slug(company.getSlug())
                .tagline(company.getTagline())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .coverImageUrl(company.getCoverImageUrl())
                .website(company.getWebsite())
                .email(company.getEmail())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .companyStatus(company.getCompanyStatus())
                .registerNumber(company.getRegisterNumber())
                .ownerId(company.getOwnerId())
                .socialLinks(SocialLinkMapper.toDTOList(company.getSocialLinks()))
                .active(company.getActive())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }

    /**
     * Request -> Entity
     */
    public static Company toEntity(CompanyRequest request, String ownerId) {

        if (request == null) {
            return null;
        }

        return Company.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .tagline(request.getTagline())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .coverImageUrl(request.getCoverImageUrl())
                .website(request.getWebsite())
                .email(request.getCompanyEmail())
                .foundedYear(request.getFoundedYear())
                .companySize(request.getCompanySize())
                .companyType(request.getCompanyType())
                .industryType(request.getIndustryType())
                .registerNumber(request.getRegisterNumber())
                .socialLinks(SocialLinkMapper.toEntityList(request.getSocialLinks()))
                .ownerId(ownerId)
                .build();
    }

    /**
     * Update existing entity from request.
     * Only non-null values are updated.
     */
    public static void updateEntity(Company company, CompanyRequest request) {

        if (request.getName() != null) {
            company.setName(request.getName());
        }

        if (request.getSlug() != null) {
            company.setSlug(request.getSlug());
        }

        if (request.getTagline() != null) {
            company.setTagline(request.getTagline());
        }

        if (request.getDescription() != null) {
            company.setDescription(request.getDescription());
        }

        if (request.getLogoUrl() != null) {
            company.setLogoUrl(request.getLogoUrl());
        }

        if (request.getCoverImageUrl() != null) {
            company.setCoverImageUrl(request.getCoverImageUrl());
        }

        if (request.getWebsite() != null) {
            company.setWebsite(request.getWebsite());
        }

        if (request.getCompanyEmail() != null) {
            company.setEmail(request.getCompanyEmail());
        }

        if (request.getFoundedYear() != null) {
            company.setFoundedYear(request.getFoundedYear());
        }

        if (request.getCompanySize() != null) {
            company.setCompanySize(request.getCompanySize());
        }

        if (request.getCompanyType() != null) {
            company.setCompanyType(request.getCompanyType());
        }

        if (request.getIndustryType() != null) {
            company.setIndustryType(request.getIndustryType());
        }

        if (request.getRegisterNumber() != null) {
            company.setRegisterNumber(request.getRegisterNumber());
        }

        if (request.getSocialLinks() != null) {
            company.setSocialLinks(request.getSocialLinks());
        }
    }
}