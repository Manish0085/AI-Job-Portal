package com.example.job.entity.embedable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobLocation {

    private String address;
    private String city;
    private String country;
    private String state;
    private String pinCode;
}
