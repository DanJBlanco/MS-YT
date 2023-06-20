package com.djblanco.securityjwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping("/access-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin(){
        return "Hello, Your access's rol is admin";
    }
    @GetMapping("/access-user")
    @PreAuthorize("hasRole('USER')")
    public String accessUser(){
        return "Hello, Your access's rol is user";
    }
    @GetMapping("/access-student")
    @PreAuthorize("hasRole('STUDENT')")
    public String accessStudent(){
        return "Hello, Your access's rol is student";
    }
}
