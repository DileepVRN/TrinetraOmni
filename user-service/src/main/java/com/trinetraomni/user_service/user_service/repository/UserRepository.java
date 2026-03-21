package com.trinetraomni.user_service.user_service.repository;

import com.trinetraomni.user_service.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // ✅ Check if email already exists
    boolean existsByEmail(String email);

    // ✅ Check if mobile already exists
    boolean existsByMobile(String mobile);

    // ✅ Find user by mobile (needed for login)
    Optional<User> findByMobile(String mobile);
}
