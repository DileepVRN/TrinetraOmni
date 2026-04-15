package com.trinetraomni.auth_service.controller;

import com.trinetraomni.auth_service.dto.AuthResponse;
import com.trinetraomni.auth_service.dto.LoginRequest;
import com.trinetraomni.auth_service.dto.UserRequest;
import com.trinetraomni.auth_service.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    // 🔐 LOGIN API
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // 🆕 REGISTER API

}