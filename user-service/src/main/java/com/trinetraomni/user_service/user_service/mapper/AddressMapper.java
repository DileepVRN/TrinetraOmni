package com.trinetraomni.user_service.user_service.mapper;

import com.trinetraomni.user_service.user_service.dto.AddressRequest;
import com.trinetraomni.user_service.user_service.dto.AddressResponse;
import com.trinetraomni.user_service.user_service.dto.UserRequest;
import com.trinetraomni.user_service.user_service.dto.UserResponse;
import com.trinetraomni.user_service.user_service.model.Address;
import com.trinetraomni.user_service.user_service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponse toDto(Address user) ;

    Address toEntity(AddressRequest dto);
}

