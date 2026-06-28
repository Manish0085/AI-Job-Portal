package com.example.job.dto.response;

import jakarta.persistence.Embeddable;
import lombok.*;

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
