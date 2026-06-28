package com.example.job.service;

import com.example.job.domain.CompanyStatus;
import com.example.job.domain.CompanyType;
import com.example.job.domain.IndustryType;
import com.example.job.dto.request.CompanyRequest;
import com.example.job.dto.response.CompanyResponse;
import com.example.job.modal.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(String ownerId, CompanyRequest request);

    CompanyResponse getCompanyById(String companyId) throws Exception;

    CompanyResponse getMyCompany(String ownerId) throws Exception;

    List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                          IndustryType industryType,
                                          CompanyStatus companyStatus);


    CompanyResponse updateCompany(String companyId,
                                  String ownerId,
                                  CompanyRequest request) throws Exception;

    CompanyResponse verifyCompany(String companyId) throws Exception;

    void deleteCompany(String companyId, String ownerId) throws Exception;
    CompanyResponse deactivateCompany(String companyId) throws Exception;

    Company getCompanyEntityById(String companyId) throws Exception;
}
