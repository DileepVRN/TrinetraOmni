package com.trinetraomni.user_service.user_service.mapper;

import com.trinetraomni.user_service.user_service.dto.UserRequest;
import com.trinetraomni.user_service.user_service.dto.UserResponse;
import com.trinetraomni.user_service.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toDto(User user);
    User toEntity(UserRequest dto);
    User toEntity(UserResponse dto);
    void updateEntityFromDto(UserRequest request, @MappingTarget User entity);

}
