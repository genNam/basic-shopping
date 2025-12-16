package com.kt.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.common.support.Preconditions;
import com.kt.domain.user.User;
import com.kt.dto.user.UserRequest;
import com.kt.repository.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;

	public void create(UserRequest.Create request){

		userRepository.save(
			User.create(
				request.loginId(),
				request.password(),
				request.name(),
				request.email(),
				request.mobile(),
				request.gender(),
				request.birthday(),
				LocalDateTime.now(),
				LocalDateTime.now()
			)
		);
	}

	public void update(UserRequest.Update request, Long id){

		var user = userRepository.findByIdOrThrow(id);

		user.update(
			request.name(), //record를 사용했기 때문에 getName()과 같은 형식으로 작성하지 않아도 됨
			request.email(),
			request.mobile()
		);
	}

	//비밀번호 수정
	public void changePassword(UserRequest.ChangePassword request, Long id){

		var user = userRepository.findByIdOrThrow(id);

		//비밀번호가 다르면 예외처리
		Preconditions.validate(user.getPassword().equals(request.newPassword()),
			ErrorCode.DOES_NOT_MATCH_OLD_PASSWORD);
		//지금 비밀번호랑 새로운 비밀번호랑 같으면 예외처리
		Preconditions.validate(!request.oldPassword().equals(request.newPassword()),
			ErrorCode.CAN_NOT_ALLOWED_SAME_PASSWORD);


		user.changePassword(request.newPassword());

	}

	//삭제
	public void delete(Long id){

		userRepository.deleteById(id);
	}


}
