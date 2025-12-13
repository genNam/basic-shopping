package com.kt.service.user;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

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


}
