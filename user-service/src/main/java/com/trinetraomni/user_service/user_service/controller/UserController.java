package com.trinetraomni.user_service.user_service.controller;

import com.trinetraomni.user_service.user_service.dto.ApiResponse;
import com.trinetraomni.user_service.user_service.dto.UserAuthResponse;
import com.trinetraomni.user_service.user_service.dto.UserRequest;
import com.trinetraomni.user_service.user_service.dto.UserResponse;
import com.trinetraomni.user_service.user_service.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "Operations related to users")
public class UserController {
    private final UserService userService;
    // ✅ Constructor Injection (BEST PRACTICE)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ 1. REGISTER USER (Used by Auth Service + React)
    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(
            @Valid @RequestBody UserRequest user) {

        System.out.println("Incoming request: " + user); // DEBUG LOG

        UserResponse savedUser = userService.registerUser(user);

        return ResponseEntity.ok(
                ApiResponse.success("User registered successfully", savedUser)
        );
    }

    // ✅ 2. GET ALL USERS
    @Operation(summary = "Fetch all users")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();

        return ResponseEntity.ok(
                ApiResponse.success("Users fetched successfully", users)
        );
    }

    // ✅ 3. GET USER BY EMAIL (Normal API)
    @Operation(summary = "Get user by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmail(
            @PathVariable String email) {

        UserResponse user = userService.getUserByEmail(email);

        return ResponseEntity.ok(
                ApiResponse.success("User fetched successfully", user)
        );
    }

    // 🔥 4. AUTH ENDPOINT (VERY IMPORTANT - used by Auth Service)
    @GetMapping("/auth/{email}")
    public ResponseEntity<UserAuthResponse> getUserAuth(@PathVariable String email) {

        UserAuthResponse user = userService.getUserAuthByEmail(email);

        return ResponseEntity.ok(user);
    }

    // ✅ 5. GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id) {

        return ResponseEntity.ok(ApiResponse.success("User fetched successfully", userService.getUserById(id)));
    }
    // ✅ 6. UPDATE USER
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest user) {
        return ResponseEntity.ok(
                ApiResponse.success("User updated successfully",
                        userService.updateUser(id, user)));
    }

    // ✅ 7. DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
    }
}