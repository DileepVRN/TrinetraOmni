package com.trinetraomni.user_service.user_service.controller;



import com.trinetraomni.user_service.user_service.dto.ApiResponse;
import com.trinetraomni.user_service.user_service.dto.UserRequest;
import com.trinetraomni.user_service.user_service.dto.UserResponse;
import com.trinetraomni.user_service.user_service.model.User;
import com.trinetraomni.user_service.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "User API", description = "Operations related to users")
@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // ✅ 1. CREATE (Register User)
    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@Valid @RequestBody UserRequest user) {

        UserResponse savedUser = userService.registerUser(user);

        return ResponseEntity.ok(
                ApiResponse.success("User registered successfully", savedUser)
        );
    }

    // ✅ 2. READ (Get All Users)
    @Operation(summary = "Fetch users")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> savedUser = userService.getAllUsers();

        return ResponseEntity.ok(
                ApiResponse.success("User details fetched successfully", savedUser)
        );
    }

    // ✅ 3. READ (Get User By ID)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ✅ 4. UPDATE User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // ✅ 5. DELETE User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}