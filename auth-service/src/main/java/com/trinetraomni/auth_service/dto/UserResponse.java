package com.trinetraomni.auth_service.dto;

import com.trinetraomni.auth_service.model.Role;

public record UserResponse(Long id,
                           String name,
                           String email,
                           String password,
                           String mobile,
                           Role role,
                           AddressResponce address) {
}
