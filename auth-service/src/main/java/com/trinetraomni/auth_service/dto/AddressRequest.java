package com.trinetraomni.auth_service.dto;

public record AddressRequest(String street,
                             String city,
                             String state,
                             String country,
                             String pincode) {
}
