package com.trinetraomni.auth_service.service;

import com.trinetraomni.auth_service.client.UserClient;
import com.trinetraomni.auth_service.dto.*;
//import com.trinetraomni.auth_service.exception.InvalidCredentialsException;
import com.trinetraomni.auth_service.exception.InvalidCredentialsException;
import com.trinetraomni.auth_service.model.Role;
import com.trinetraomni.auth_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserClient userClient;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // 🔐 LOGIN
    public AuthResponse login(LoginRequest request) {

        // 1️⃣ Fetch user from user-service
        UserAuthResponse user = userClient.getUserAuthByEmail(request.email());

        // 2️⃣ Validate user existence
        if (user == null || user.password() == null) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // 3️⃣ Validate password
        if (!passwordEncoder.matches(request.password(), user.password())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // 4️⃣ Handle role safely
        Role role = user.role() != null
                ? Role.valueOf(user.role())
                : Role.USER;

        // 5️⃣ Generate JWT token
        String token = jwtUtil.generateToken(user.email(), role.name());

        // 6️⃣ Return response
        return new AuthResponse(token, user.email(), role);
    }
}