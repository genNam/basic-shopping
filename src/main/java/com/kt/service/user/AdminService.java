package com.kt.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kt.common.exception.ErrorCode;
import com.kt.common.support.BaseEntity;
import com.kt.common.support.Preconditions;
import com.kt.domain.user.User;
import com.kt.dto.user.UserRequest;
import com.kt.dto.user.UserResponse;
import com.kt.repository.user.UserRepository;

//트랜잭션 어노테이션
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService extends BaseEntity {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;



}
