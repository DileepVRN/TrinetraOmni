package com.trinetraomni.user_service.user_service.dto;



import com.trinetraomni.user_service.user_service.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record UserRequest(
        @Schema(example = "Enter name")
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 50)
        String name,
        @Schema(example = "email@gmail.com")
        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,
        @Schema(example = "9876543210")
        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
        String mobile,
        @Schema(example = "123456")
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,
        @Schema(example = "Enter Role")
        Role role

) {}
