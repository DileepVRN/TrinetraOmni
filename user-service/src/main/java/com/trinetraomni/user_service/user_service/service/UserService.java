package com.trinetraomni.user_service.user_service.service;


import com.trinetraomni.user_service.user_service.exception.UserNotFoundException;
import com.trinetraomni.user_service.user_service.model.User;
import com.trinetraomni.user_service.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User registerUser(User user) {
        return userRepository.save(user);

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
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
