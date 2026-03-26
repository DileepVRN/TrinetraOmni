package com.trinetraomni.user_service.user_service.service;

import com.trinetraomni.user_service.user_service.dto.UserAuthResponse;
import com.trinetraomni.user_service.user_service.dto.UserRequest;
import com.trinetraomni.user_service.user_service.dto.UserResponse;
import com.trinetraomni.user_service.user_service.exception.EmailAlreadyExistsException;
import com.trinetraomni.user_service.user_service.exception.MobileAlreadyExistsException;
import com.trinetraomni.user_service.user_service.exception.UserNotFoundException;
import com.trinetraomni.user_service.user_service.mapper.UserMapper;
import com.trinetraomni.user_service.user_service.model.User;
import com.trinetraomni.user_service.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    // ========================= REGISTER USER =========================
    public UserResponse registerUser(UserRequest userRequest) {

        // ✅ Validate email & mobile
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new EmailAlreadyExistsException(userRequest.email());
        }

        if (userRepository.existsByMobile(userRequest.mobile())) {
            throw new MobileAlreadyExistsException(userRequest.mobile());
        }

        // ✅ Map DTO to Entity
        User userEntity = mapper.toEntity(userRequest);

        // ✅ Hash password before saving
        userEntity.setPassword(passwordEncoder.encode(userRequest.password()));

        // ✅ Save user
        User savedUser = userRepository.save(userEntity);

        return mapper.toDto(savedUser);
    }

    // ========================= GET ALL USERS =========================
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // ========================= GET USER BY ID =========================
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toDto(user);
    }

    // ========================= GET USER BY EMAIL =========================
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Email", email));
        return mapper.toDto(user);
    }

    // ========================= UPDATE USER =========================
    public UserResponse updateUser(Long id, UserRequest userRequest) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // ✅ Check email/mobile conflicts
        if (!existingUser.getEmail().equals(userRequest.email()) &&
                userRepository.existsByEmail(userRequest.email())) {
            throw new EmailAlreadyExistsException(userRequest.email());
        }

        if (!existingUser.getMobile().equals(userRequest.mobile()) &&
                userRepository.existsByMobile(userRequest.mobile())) {
            throw new MobileAlreadyExistsException(userRequest.mobile());
        }

        // ✅ Update fields using mapper
        mapper.updateEntityFromDto(userRequest, existingUser);

        // ✅ Hash password if updated
        if (userRequest.password() != null && !userRequest.password().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(userRequest.password()));
        }

        User updatedUser = userRepository.save(existingUser);

        return mapper.toDto(updatedUser);
    }

    // ========================= DELETE USER =========================
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    // ========================= AUTH USER (FOR AUTH SERVICE) =========================
    public UserAuthResponse getUserAuthByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Email", email));

        return new UserAuthResponse(
                user.getEmail(),
                user.getPassword(), // ✅ hashed password
                user.getRole().toString()
        );
    }
}
