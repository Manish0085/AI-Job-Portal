package com.example.job.entity.embedable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaryRange {

    private BigDecimal minSalary;
    private BigDecimal maxSalary;

//    private String currency;
}
