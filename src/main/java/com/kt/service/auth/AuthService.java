package com.kt.service.auth;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.tuple.Pair;
import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.common.support.Preconditions;
import com.kt.common.support.redis.RedisService;
import com.kt.dto.auth.TokenReissueRequest;
import com.kt.dto.auth.TokenResponse;
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
	private final RedisService redisService;

	public Pair<String, String> login(String loginId, String password){

		var user = userRepository.findByLoginId(loginId)
			.orElseThrow(() -> new CustomException(ErrorCode.FAIL_LOGIN));

		Preconditions.validate(passwordEncoder.matches(
			password, user.getPassword()), (ErrorCode.FAIL_LOGIN));

		var accessToken = jwtService.issue(user.getId(), user.getRole(), jwtService.getAccessExpiration());
		var refreshToken = jwtService.issue(user.getId(), user.getRole(), jwtService.getRefreshExpiration());

		//리프레시 토큰의 만료시간 계산
		Long ttlSeconds = (jwtService.getRefreshExpiration().getTime()//리프레시 토큰의 만료시각을 Date 타입으로 반환 -> 밀리초 단위의 시간을 long으로 반환
			- new Date().getTime())/1000; //밀리초 단위를 초(s)로 바꾸기 위해 1000으로 나눔
		redisService.saveRefreshToken(refreshToken,user.getId(),ttlSeconds);

		return Pair.of(accessToken, refreshToken);
	}

	//재발급
	public TokenResponse reissue(TokenReissueRequest request){

		String oldRefreshToken = request.refreshToken();

		//변환 한 문자열이 ‘서명이 있는 토큰이며, 현재 서버의 시크릿 키로 검증 가능한가?’를 확인하는 메서드
		jwtService.validate(oldRefreshToken);

		var userId = redisService.findUserIdByRefreshToken(oldRefreshToken);
		if(userId == null || !userId.equals(jwtService.parseId(oldRefreshToken))){
			throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
		}

		redisService.deleteRefreshToken(oldRefreshToken);

		var user = userRepository.findByIdOrThrow(userId);

		//토큰 새로 생성
		var accessToken = jwtService.issue(user.getId(), user.getRole(), jwtService.getAccessExpiration());
		var refreshToken = jwtService.issue(user.getId(), user.getRole(), jwtService.getRefreshExpiration());

		Long ttlSeconds = (jwtService.getRefreshExpiration().getTime() - new Date().getTime()) / 1000;
		redisService.saveRefreshToken(refreshToken, userId, ttlSeconds);

		return TokenResponse.of(accessToken,refreshToken);
	}
}
