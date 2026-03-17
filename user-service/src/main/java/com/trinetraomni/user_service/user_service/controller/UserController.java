package com.trinetraomni.user_service.user_service.controller;



import com.trinetraomni.user_service.user_service.model.User;
import com.trinetraomni.user_service.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ 1. CREATE (Register User)
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // ✅ 2. READ (Get All Users)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
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