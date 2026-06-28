package com.example.job.service.impl;

import com.example.job.domain.CompanyStatus;
import com.example.job.domain.CompanyType;
import com.example.job.domain.IndustryType;
import com.example.job.dto.request.CompanyRequest;
import com.example.job.dto.response.CompanyResponse;
import com.example.job.mapper.CompanyMapper;
import com.example.job.mapper.SocialLinkMapper;
import com.example.job.modal.Company;
import com.example.job.repository.CompanyRepository;
import com.example.job.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {


    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public CompanyResponse createCompany(String ownerId, CompanyRequest request) {
        if (companyRepository.existsByOwnerId(ownerId)) {
            throw new IllegalArgumentException("You have already registered a company.");
        }

        // Check duplicate company name
        if (companyRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Company name already exists.");
        }

        // Check duplicate slug
        if (companyRepository.existsBySlug(request.getSlug())) {
            throw new IllegalArgumentException("Company slug already exists.");
        }

        // Check duplicate registration number
        if (companyRepository.existsByRegisterNumber(request.getRegisterNumber())) {
            throw new IllegalArgumentException("Registration number already exists.");
        }

        // Check duplicate company email
        if (companyRepository.existsByEmail(request.getCompanyEmail())) {
            throw new IllegalArgumentException("Company email already exists.");
        }

        String slug = generateUniqueSlug(request.getName());

        Company company = Company.builder()
                .name(request.getName())
                .slug(slug)
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
//                .socialLinks(SocialLinkMapper.toEntityList(request.getSocialLinks()))
                .ownerId(ownerId)
                .companyStatus(CompanyStatus.ACTIVE)
                .active(true)
                .build();

        Company savedCompany = companyRepository.save(company);

        return CompanyMapper.toDTO(savedCompany);
    }

    private String generateUniqueSlug(String name) {

        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        if (!companyRepository.existsBySlug(base)) {
            return base;
        }

        int counter = 1;

        while (companyRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }

        return base + "-" + counter;
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse getCompanyById(String companyId) throws Exception {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new Exception("Company not found with id : " + companyId));

        return CompanyMapper.toDTO(company);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse getMyCompany(String ownerId) throws Exception {

        Company company = companyRepository.findByOwnerId(ownerId)
                .orElseThrow(() ->
                        new Exception(
                                "No company found for owner with id: " + ownerId));

        return CompanyMapper.toDTO(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> getAllCompanies(
            CompanyType companyType,
            IndustryType industryType,
            CompanyStatus companyStatus) {

        List<Company> companies = companyRepository.findByFilters(
                companyType,
                industryType,
                companyStatus
        );

        return companies.stream()
                .map(CompanyMapper::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public CompanyResponse updateCompany(
            String companyId,
            String ownerId,
            CompanyRequest request) throws Exception {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new Exception(
                                "Company not found with id : " + companyId));

        // Ownership check
        if (!company.getOwnerId().equals(ownerId)) {
            throw new Exception(
                    "You are not authorized to update this company.");
        }

        // Check duplicate company name
        if (request.getName() != null
                && !request.getName().equalsIgnoreCase(company.getName())
                && companyRepository.existsByName(request.getName())) {

            throw new Exception("Company name already exists.");
        }

        // Check duplicate slug
        if (request.getSlug() != null
                && !request.getSlug().equalsIgnoreCase(company.getSlug())
                && companyRepository.existsBySlug(request.getSlug())) {

            throw new Exception("Company slug already exists.");
        }

        // Check duplicate company email
        if (request.getCompanyEmail() != null
                && !request.getCompanyEmail().equalsIgnoreCase(company.getEmail())
                && companyRepository.existsByEmail(request.getCompanyEmail())) {

            throw new Exception("Company email already exists.");
        }

        // Check duplicate registration number
        if (request.getRegisterNumber() != null
                && !request.getRegisterNumber().equals(company.getRegisterNumber())
                && companyRepository.existsByRegisterNumber(request.getRegisterNumber())) {

            throw new Exception("Registration number already exists.");
        }

        // Update only provided fields
        CompanyMapper.updateEntity(company, request);

        Company updatedCompany = companyRepository.save(company);

        return CompanyMapper.toDTO(updatedCompany);
    }

    @Override
    @Transactional
    public CompanyResponse verifyCompany(String companyId) throws Exception {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new Exception(
                                "Company not found with id : " + companyId));

        if (company.getCompanyStatus() == CompanyStatus.ACTIVE) {
            throw new IllegalStateException("Company is already verified.");
        }

        company.setCompanyStatus(CompanyStatus.ACTIVE);
        company.setActive(true);
        company.setVerified(true);

        Company updatedCompany = companyRepository.save(company);

        return CompanyMapper.toDTO(updatedCompany);
    }



    @Override
    @Transactional
    public void deleteCompany(String companyId, String ownerId) throws Exception {

        Company company = getCompanyEntityById(companyId);
        assertOwner(company, ownerId);
        companyRepository.delete(company);


    }

    private void assertOwner(Company company, String ownerId) throws Exception {

        if (!company.getOwnerId().equals(ownerId)) {
            throw new Exception("You are not the owner of this company");
        }

    }

    @Override
    @Transactional
    public CompanyResponse deactivateCompany(String companyId) throws Exception {

        Company company = getCompanyEntityById(companyId);

        if (!company.getActive()) {
            throw new IllegalStateException("Company is already deactivated.");
        }

        company.setActive(false);
        company.setCompanyStatus(CompanyStatus.SUSPENDED);

        return CompanyMapper.toDTO(
                companyRepository.save(company)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Company getCompanyEntityById(String companyId) throws Exception {

        return companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new Exception(
                                "Company not found with id : " + companyId));
    }
}
