package com.kt.controller.auth;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.auth.LoginRequest;
import com.kt.dto.auth.LoginResponse;
import com.kt.dto.auth.TokenReissueRequest;
import com.kt.dto.auth.TokenResponse;
import com.kt.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends SwaggerAssistance {

	private final AuthService authService;

	@PostMapping("/login")
	public ApiResult<LoginResponse> login(@RequestBody @Valid LoginRequest request){

		var pair = authService.login(request.loginId(), request.password());

		return ApiResult.ok(new LoginResponse(pair.getLeft(), pair.getRight())); //응답객체 생성

	}

	//로그인시 한번 발급 받은 refresh token을 통해 "새 access token"을 재발급 받음
	//엑세스 토큰 : 실제 api 요청 시 사용, 유효기간 짧음
	//리프레시 토큰 : access 만료시 재발급용, 유효기간 김
	@PatchMapping("/refresh")
	public ApiResult<TokenResponse> reissue(@RequestBody @Valid TokenReissueRequest request){

		TokenResponse response = authService.reissue(request);

		return ApiResult.ok(response);

	}
}
