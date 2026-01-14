package com.kt.controller.user;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.user.UserResponse;
import com.kt.security.CurrentUser;
import com.kt.service.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User")
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController extends SwaggerAssistance {

	private final UserService userService;

	//관리자가 '유저들'을 조회
	@GetMapping
	public ApiResult<List<UserResponse>> adminUserSearch(
		@AuthenticationPrincipal CurrentUser currentUser
	){
		userService;

		return ApiResult.ok();
	}

}
