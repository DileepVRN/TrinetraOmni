package com.trinetraomni.auth_service.dto;


import com.trinetraomni.auth_service.model.Role;

public record AuthResponse(
        String token,
        String email,
        Role role
) {}
