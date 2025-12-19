package com.kt.service.user;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kt.common.support.BaseEntity;
import com.kt.domain.user.User;
import com.kt.dto.user.UserRequest;
import com.kt.repository.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService extends BaseEntity {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void create(UserRequest.Create request){

		var newAdmin = User.createAdmin(
			request.loginId(),
			passwordEncoder.encode(request.password()), //비밀번호를 암호화하는 메서드
			request.name(),
			request.email(),
			request.mobile(),
			request.gender(),
			request.birthday(),
			LocalDateTime.now(),
			LocalDateTime.now()
		);

		userRepository.save(newAdmin);
	}
}
