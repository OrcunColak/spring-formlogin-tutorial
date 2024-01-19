package com.colak.springformlogintutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    // http://localhost:8080/admin/dashboard
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }
}
