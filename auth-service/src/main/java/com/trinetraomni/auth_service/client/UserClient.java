package com.trinetraomni.auth_service.client;

import com.trinetraomni.auth_service.dto.ApiResponse;
import com.trinetraomni.auth_service.dto.UserAuthResponse;
import com.trinetraomni.auth_service.dto.UserRequest;
import com.trinetraomni.auth_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/*
@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    @PostMapping("/users")
    UserResponce createUser(@RequestBody UserRequest request);

    @GetMapping("/users/email/{email}")
    UserResponce getUserByEmail(@PathVariable String email);
}*/
@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    @PostMapping("/users/register")
    ApiResponse<UserResponse> createUser(@RequestBody UserRequest request);

    @GetMapping("/users/email/{email}")
    ApiResponse<UserResponse> getUserByEmail(@PathVariable String email);
    @GetMapping("/users/auth/{email}")
    UserAuthResponse getUserAuthByEmail(@PathVariable String email);
}
