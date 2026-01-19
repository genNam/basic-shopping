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



	//관리자가 회원 비밀번호 초기화
	public void adminUserInitPassword(Long userId){

		var user = userRepository.findByIdOrThrow(userId);
	}

	//관리자가 회원 비밀번호 변경
	public void adminUserChangePassword(Long userId, UserRequest.ChangePassword request){

		var user = userRepository.findByIdOrThrow(userId);

		//비밀번호가 다르면 예외처리
		Preconditions.validate(user.getPassword().equals(request.oldPassword()), ErrorCode.DOES_NOT_MATCH_OLD_PASSWORD);
		//비밀번호가 같으면 예외처리
		Preconditions.validate(request.oldPassword().equals(request.newPassword()), ErrorCode.CAN_NOT_ALLOWED_SAME_PASSWORD);

		user.changePassword(request.newPassword());
	}
}
