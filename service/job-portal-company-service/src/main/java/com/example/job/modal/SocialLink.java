package com.example.job.modal;

import com.example.job.domain.SocialPlatform;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class SocialLink {

    private SocialPlatform socialPlatform;
    private String url;

    //TODO [Reverse Engineering] generate columns from DB
}