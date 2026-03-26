package com.trinetraomni.auth_service.dto;

import com.trinetraomni.auth_service.model.Role;

public record UserRequest(String name,
                          String email,
                          String password,
                          String mobile,
                          Role role,
                          AddressRequest address) {}
