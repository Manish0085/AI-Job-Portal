package com.example.job.controller;

import com.example.job.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String get() {
        return "Running " + UserRole.ROLE_JOB_SEEKER;
    }
}
