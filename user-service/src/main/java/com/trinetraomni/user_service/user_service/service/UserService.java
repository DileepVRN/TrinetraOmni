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

    private UserMapper mapper;
    public UserResponse registerUser(UserRequest user) {

        return mapper.toDto(userRepository.save(mapper.toEntity(user)));

    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll().stream().map(user->
                mapper.toDto(user)).toList();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Long id, User user) {
          User existingUser= getUserById(id);
        return  existingUser=user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
