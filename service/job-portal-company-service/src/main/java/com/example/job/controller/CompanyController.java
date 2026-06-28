package com.example.job.controller;

import com.example.job.domain.CompanyStatus;
import com.example.job.domain.CompanyType;
import com.example.job.domain.IndustryType;
import com.example.job.dto.ApiResponse;
import com.example.job.dto.request.CompanyRequest;
import com.example.job.dto.response.CompanyResponse;
import com.example.job.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    /**
     * URL : POST /api/companies
     * Description : Register a new company
     * Access : USER
     */
    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(
            @RequestHeader("X-User-Id") String ownerId,
            @Valid @RequestBody CompanyRequest request) {

        CompanyResponse response = companyService.createCompany(ownerId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * URL : GET /api/companies/me
     * Description : Get logged-in user's company
     * Access : USER
     */
    @GetMapping("/me")
    public ResponseEntity<CompanyResponse> getMyCompany(
            @RequestHeader("X-User-Id") String ownerId) throws Exception {

        return ResponseEntity.ok(
                companyService.getMyCompany(ownerId)
        );
    }

    /**
     * URL : GET /api/companies/{companyId}
     * Description : Get company by id
     * Access : PUBLIC
     */
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> getCompanyById(
            @PathVariable String companyId) throws Exception {

        return ResponseEntity.ok(
                companyService.getCompanyById(companyId)
        );
    }

    /**
     * URL : GET /api/companies
     * Description : Get all companies
     * Access : PUBLIC
     *
     * Examples:
     * GET /api/companies
     * GET /api/companies?companyType=PRIVATE
     * GET /api/companies?industryType=TECHNOLOGY
     * GET /api/companies?companyStatus=VERIFIED
     */
    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies(

            @RequestParam(required = false)
            CompanyType companyType,

            @RequestParam(required = false)
            IndustryType industryType,

            @RequestParam(required = false)
            CompanyStatus companyStatus) {

        return ResponseEntity.ok(
                companyService.getAllCompanies(
                        companyType,
                        industryType,
                        companyStatus
                )
        );
    }

    /**
     * URL : PATCH /api/companies/{companyId}
     * Description : Update company
     * Access : OWNER
     */
    @PatchMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> updateCompany(

            @PathVariable String companyId,

            @RequestHeader("X-User-Id") String ownerId,

            @Valid @RequestBody CompanyRequest request) throws Exception {

        return ResponseEntity.ok(
                companyService.updateCompany(
                        companyId,
                        ownerId,
                        request
                )
        );
    }

    /**
     * URL : PATCH /api/companies/{companyId}/verify
     * Description : Verify company
     * Access : ADMIN
     */
    @PatchMapping("/{companyId}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(
            @PathVariable String companyId) throws Exception {

        return ResponseEntity.ok(
                companyService.verifyCompany(companyId)
        );
    }

    /**
     * URL : PATCH /api/companies/{companyId}/deactivate
     * Description : Deactivate company
     * Access : ADMIN
     */
    @PatchMapping("/{companyId}/deactivate")
    public ResponseEntity<CompanyResponse> deactivateCompany(
            @PathVariable String companyId) throws Exception {

        return ResponseEntity.ok(
                companyService.deactivateCompany(companyId)
        );
    }

    /**
     * URL : DELETE /api/companies/{companyId}
     * Description : Delete company
     * Access : ADMIN
     */
    @DeleteMapping("/{companyId}")
    public ResponseEntity<ApiResponse> deleteCompany(
            @PathVariable String companyId,
            @RequestHeader("X-User-Id") String ownerId) throws Exception {

        companyService.deleteCompany(companyId, ownerId);

        return ResponseEntity.noContent().build();
    }

}