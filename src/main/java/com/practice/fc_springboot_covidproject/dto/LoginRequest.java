package com.practice.fc_springboot_covidproject.dto;

public record LoginRequest(
        String email,
        String password
) {
    public static LoginRequest of(
            String email,
            String password
    ) {
        return new LoginRequest(email, password);
    }
}