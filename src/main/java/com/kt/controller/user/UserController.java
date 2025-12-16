package com.kt.controller.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.kt.common.response.ApiResult;
import com.kt.dto.user.UserRequest;
import com.kt.security.CurrentUser;
import com.kt.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "User")
@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public ApiResult<Void> create(@RequestBody @Valid UserRequest.Create request){

		userService.create(request);

		return ApiResult.ok();
	}

	//내 정보 수정
	@PutMapping("/my-info")
	public ApiResult<Void> update(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid UserRequest.Update request){

		userService.update(request,currentUser.getId());

		return ApiResult.ok();
	}

	//비밀번호 변경
	@PutMapping("/change-password")
	public ApiResult<Void> changePassword(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid UserRequest.ChangePassword request){

		userService.changePassword(request, currentUser.getId());

		return ApiResult.ok();

	}

}
