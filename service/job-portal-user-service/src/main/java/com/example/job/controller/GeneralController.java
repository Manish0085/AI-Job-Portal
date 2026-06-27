package com.example.job.controller;

import com.example.job.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GeneralController {


    @GetMapping
    public String getHealth() {
        return "Running " + UserRole.ROLE_ADMIN;
    }
}
