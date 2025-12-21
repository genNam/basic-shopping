package com.kt.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import ch.qos.logback.core.joran.sanity.Pair;
import org.apache.commons.lang3.tuple.Pair;
import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.common.support.Preconditions;
import com.kt.repository.user.UserRepository;
import com.kt.security.JwtService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public Pair<String, String> login(String loginId, String password){

		var user = userRepository.findByLoginId(loginId)
			.orElseThrow(() -> new CustomException(ErrorCode.FAIL_LOGIN));

		Preconditions.validate(passwordEncoder.matches(
			password, user.getPassword()), (ErrorCode.FAIL_LOGIN));

		var accessToken = jwtService.issue(user.getId(), user.getRole(), jwtService.getAccessExpiration());
		var refreshToken = jwtService.issue(user.getId(), user.getRole(), jwtService.getRefreshExpiration());

		return Pair.of(accessToken, refreshToken);
	}
}
