package com.example.job.mapper;

import com.example.job.dto.response.SocialLinkResponse;
import com.example.job.modal.SocialLink;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class SocialLinkMapper {

    private SocialLinkMapper() {
    }

    /**
     * Entity -> DTO
     */
    public static SocialLinkResponse toDTO(SocialLink socialLink) {

        if (socialLink == null) {
            return null;
        }

        return SocialLinkResponse.builder()
                .socialPlatform(socialLink.getSocialPlatform())
                .url(socialLink.getUrl())
                .build();
    }

    /**
     * DTO -> Entity
     */
    public static SocialLink toEntity(SocialLink request) {

        if (request == null) {
            return null;
        }

        return new SocialLink(
                request.getSocialPlatform(),
                request.getUrl()
        );
    }

    /**
     * List<Entity> -> List<DTO>
     */
    public static List<SocialLinkResponse> toDTOList(List<SocialLink> socialLinks) {

        if (socialLinks == null || socialLinks.isEmpty()) {
            return Collections.emptyList();
        }

        return socialLinks.stream()
                .map(SocialLinkMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * List<DTO> -> List<Entity>
     */
    public static List<SocialLink> toEntityList(List<SocialLink> requests) {

        if (requests == null || requests.isEmpty()) {
            return Collections.emptyList();
        }

        return requests.stream()
                .map(SocialLinkMapper::toEntity)
                .collect(Collectors.toList());
    }
}