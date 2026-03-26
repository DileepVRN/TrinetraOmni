package com.trinetraomni.user_service.user_service.service;


import com.trinetraomni.user_service.user_service.dto.UserAuthResponse;
import com.trinetraomni.user_service.user_service.dto.UserRequest;
import com.trinetraomni.user_service.user_service.dto.UserResponse;
import com.trinetraomni.user_service.user_service.exception.UserNotFoundException;
import com.trinetraomni.user_service.user_service.mapper.UserMapper;
import com.trinetraomni.user_service.user_service.model.User;
import com.trinetraomni.user_service.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;


    /*public UserResponse registerUser(UserRequest user) {

        return mapper.toDto(userRepository.save(mapper.toEntity(user)));

    }*/
    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toDto(user);
    }
    public UserResponse registerUser(UserRequest user) {

        // ✅ Check if email already exists
        if (userRepository.existsByEmail(user.email())) {
            throw new RuntimeException("Email already exists");
        }

        // ✅ Check if mobile already exists
        if (userRepository.existsByMobile(user.mobile())) {
            throw new RuntimeException("Mobile number already exists");
        }

        // ✅ Save user
        System.out.print(user.toString());
        return mapper.toDto(userRepository.save(mapper.toEntity(user)));
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll().stream().map(user->
                mapper.toDto(user)).toList();
    }

    public UserResponse getUserById(Long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {

        // 1️⃣ Fetch existing user entity
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        // ✅ Check email (only if changed)
        if (!existingUser.getEmail().equals(userRequest.email()) &&
                userRepository.existsByEmail(userRequest.email())) {
            throw new RuntimeException("Email already exists");
        }

        // ✅ Check mobile (only if changed)
        if (!existingUser.getMobile().equals(userRequest.mobile()) &&
                userRepository.existsByMobile(userRequest.mobile())) {
            throw new RuntimeException("Mobile number already exists");
        }

        // 2️⃣ Update fields using your mapper
        mapper.updateEntityFromDto(userRequest, existingUser);
        // OR manually:
        // existingUser.setName(userRequest.getName());
        // existingUser.setEmail(userRequest.getEmail());
        // ... etc

        // 3️⃣ Save updated entity
        User updatedUser = userRepository.save(existingUser);

        // 4️⃣ Convert to response DTO
        return mapper.toDto(updatedUser);
    }
    public UserAuthResponse getUserAuthByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserAuthResponse(
                user.getEmail(),
                user.getPassword(), // ✅ encoded password
                user.getRole().toString()
        );
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
