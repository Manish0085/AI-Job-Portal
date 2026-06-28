package com.example.job.dto.response;

import com.example.job.domain.SocialPlatform;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLinkResponse {

    private SocialPlatform socialPlatform;
    private String url;
}
