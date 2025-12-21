package com.kt.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.auth.LoginRequest;
import com.kt.dto.auth.LoginResponse;
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
}
