package com.trinetraomni.user_service.user_service.dto;
import com.trinetraomni.user_service.user_service.model.Address;
public record AddressRequest(
    String street,
    String city,
    String state,
    String country,
    String pincode
){}
