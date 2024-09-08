package com.practice.fc_springboot_covidproject.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApiAuthController {

    @GetMapping("/sign-up")
    public String signUp() {
        return "siun-up completed!";
    }

    @GetMapping("/login")
    public String login() {
        return "login completed!";
    }
}
