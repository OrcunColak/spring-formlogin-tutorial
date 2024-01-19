package com.colak.springformlogintutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // http://localhost:8080/user/dashboard
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
}
