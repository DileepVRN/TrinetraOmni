package com.trinetraomni.user_service.user_service.exception;

import com.trinetraomni.user_service.user_service.ErrorResponse;
import com.trinetraomni.user_service.user_service.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GobalExceptionHandler {

    // 🔴 404 - User Not Found
    @ExceptionHandler(UserNotFoundException.class)
    public ApiResponse<?> handleUserNotFound(UserNotFoundException ex) {
        return ApiResponse.failure(ex.getMessage());
    }

    // 🔴 400 - Email Exists
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ApiResponse<?> handleEmailExists(EmailAlreadyExistsException ex) {
        return ApiResponse.failure(ex.getMessage());
    }

    // 🔴 400 - Mobile Exists
    @ExceptionHandler(MobileAlreadyExistsException.class)
    public ApiResponse<?> handleMobileExists(MobileAlreadyExistsException ex) {
        return ApiResponse.failure(ex.getMessage());
    }

    // 🔴 400 - Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        return ApiResponse.failure(fieldErrors.toString());
    }

    // 🔴 400 - JSON Error
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<?> handleJsonError(HttpMessageNotReadableException ex) {
        return ApiResponse.failure("Malformed JSON");
    }

    // 🔴 Feign 404 (VERY IMPORTANT FOR YOUR ISSUE)
    @ExceptionHandler(feign.FeignException.NotFound.class)
    public ApiResponse<?> handleFeignNotFound(Exception ex) {
        return ApiResponse.failure("User is not found");
    }

    // 🔴 500 - Generic
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneral(Exception ex) {
        return ApiResponse.failure("Something went wrong: " + ex.getMessage());
    }
}
