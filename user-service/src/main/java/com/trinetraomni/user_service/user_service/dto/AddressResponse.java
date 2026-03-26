package com.trinetraomni.user_service.user_service.dto;

public record AddressResponse(
        String street,
        String city,
        String state,
        String country,
        String pincode
) {}
