package com.kt.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.user.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
