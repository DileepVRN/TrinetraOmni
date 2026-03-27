package com.trinetraomni.auth_service.service;

import com.trinetraomni.auth_service.client.UserClient;
import com.trinetraomni.auth_service.dto.*;
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

    // 🔐 REGISTER
    /*public AuthResponse register(UserRequest request) {

        // 1️⃣ Encode password
        String encodedPassword = passwordEncoder.encode(request.password());

        // 2️⃣ FIXED ORDER ✅
        UserRequest newRequest = new UserRequest(

                request.name(),
                request.email(),
                request.mobile(),       // ✅
                encodedPassword,        // ✅
                request.role(),
                request.address()
        );

        // 3️⃣ Call user-service
        //UserResponse user = userClient.createUser(newRequest);
        ApiResponse<UserResponse> response = userClient.createUser(request);
        UserResponse user = response.getData();

        // 4️⃣ NULL SAFE ROLE ✅
        String role = user.role() != null ? user.role().toString() : "USER";

        // 5️⃣ Generate JWT
        String token = jwtUtil.generateToken(
                user.email(),
                role
        );

        // 6️⃣ Return response
        return new AuthResponse(token, user.email(), user.role());
    }*/

    // 🔐 LOGIN
    public AuthResponse login(LoginRequest request) {

        UserAuthResponse user = userClient.getUserAuthByEmail(request.email());

        if (user == null || user.password() == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(request.password(), user.password())) {
            throw new RuntimeException("Invalid credentials");
        }
        Role role = user.role() != null
                ? Role.valueOf(user.role())
                : Role.USER;
        String token = jwtUtil.generateToken(user.email(), user.role());
        return new AuthResponse(token, user.email(),role);
    }
}