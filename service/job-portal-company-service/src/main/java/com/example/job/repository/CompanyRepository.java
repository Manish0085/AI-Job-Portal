package com.example.job.repository;

import com.example.job.domain.CompanyStatus;
import com.example.job.domain.CompanyType;
import com.example.job.domain.IndustryType;
import com.example.job.modal.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    Optional<Company> findByOwnerId(String ownerId);

    boolean existsByOwnerId(String ownerId);

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    boolean existsByRegisterNumber(String registerNumber);

    @Query("""
            SELECT c
            FROM Company c
            WHERE
                (:companyType IS NULL OR c.companyType = :companyType)
            AND (:industryType IS NULL OR c.industryType = :industryType)
            AND (:status IS NULL OR c.companyStatus = :status)
            """)
    List<Company> findByFilters(
            @Param("companyType") CompanyType companyType,
            @Param("industryType") IndustryType industryType,
            @Param("status") CompanyStatus status
    );


    boolean existsByEmail(String companyEmail);
}
