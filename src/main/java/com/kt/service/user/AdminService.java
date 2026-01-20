package com.kt.service.user;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.common.support.BaseEntity;
import com.kt.domain.user.Role;
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

	//관리자 생성
	public void createAdmin(UserRequest.CreateAdmin request){

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

	//관리자 목록 조회
	@Transactional(readOnly = true)
	public List<UserResponse.AdminUserSearch> adminsSearch(){

		var adminList = userRepository.findByRole(Role.ADMIN);

		var response = adminList.stream()
			.map(a -> UserResponse.AdminUserSearch.from(a))
			.toList();

		return response;

	}

	//관리자 상세 조회
	@Transactional(readOnly = true)
	public UserResponse.AdminUserDetail adminDetail(Long adminId){

		var admin = userRepository.findByRoleAndUserId(Role.ADMIN,adminId).
			orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		return UserResponse.AdminUserDetail.from(admin);
	}



}
