package com.trinetraomni.user_service.user_service.repository;

import com.trinetraomni.user_service.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
