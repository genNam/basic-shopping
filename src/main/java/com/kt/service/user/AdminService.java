package com.kt.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kt.common.support.BaseEntity;
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

	//관리자의 회원 목록 조회
	@Transactional(readOnly = true)
	public List<UserResponse.AdminUserSearch> adminUserSearch(){

		var userList = userRepository.findAll();

		var response = userList.stream()
			.map(u -> UserResponse.AdminUserSearch.from(u))
			.toList();

		return response;
	}

	//관리자의 회원 상세 조회
	@Transactional(readOnly = true)
	public UserResponse.AdminUserDetail adminUserDetail(Long userId){

		var user = userRepository.findByIdOrThrow(userId);

		return UserResponse.AdminUserDetail.from(user);
	}

	//관리자 회원 정보 수정
	public void adminUserUpdate(Long userId){

		var user = userRepository.findByIdOrThrow(userId);

		//회원정보 수정(업데이트)
		user.update(user.getName(), user.getEmail(), user.getMobile());
	}
}
