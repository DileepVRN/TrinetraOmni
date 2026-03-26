package com.trinetraomni.user_service.user_service.dto;


import com.trinetraomni.user_service.user_service.model.Role;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        String mobile,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        AddressResponse address
        //String password

) {}