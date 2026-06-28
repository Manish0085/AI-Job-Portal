package com.example.job.modal;

import com.example.job.domain.SocialPlatform;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.job.domain.SocialPlatform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class SocialLink {

    @NotNull(message = "Social platform is required")
    private SocialPlatform socialPlatform;

    @NotBlank(message = "URL is required")
    @Pattern(
            regexp = "^(https?://.*)$",
            message = "Please provide a valid URL"
    )
    private String url;
}