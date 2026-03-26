package com.trinetraomni.user_service.user_service.dto;

public record UserAuthResponse( String email,
                                String password,
                                String role) {
}
