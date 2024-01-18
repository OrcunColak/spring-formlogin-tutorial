package com.colak.springformlogintutorial.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    // http://localhost:8080/api/greetAdmin
    @GetMapping("/greetAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String greetAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/greetAdminOrUser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String greetAdminOrUser() {
        return "Hello Admin or User";
    }
}