package com.example.job.entity;

import com.example.job.modal.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobCategory extends BaseEntity {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    private String description;

    private String iconUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private JobCategory parent;

    @Builder.Default
    @OneToMany(
            mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<JobCategory> subCategories = new ArrayList<>();

    @Builder.Default
    private Boolean active = true;
}