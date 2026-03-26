package com.trinetraomni.auth_service.dto;

public record UserAuthResponse(
        String email,
           String password,
           String role) {}
