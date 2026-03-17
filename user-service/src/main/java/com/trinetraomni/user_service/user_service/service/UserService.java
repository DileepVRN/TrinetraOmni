package com.trinetraomni.user_service.user_service.service;


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

    public UserResponse registerUser(UserRequest user) {

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

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
