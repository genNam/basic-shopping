package com.kt.controller.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.user.UserRequest;
import com.kt.dto.user.UserResponse;
import com.kt.service.user.AdminService;
import com.kt.service.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Admin-User")
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController extends SwaggerAssistance {

	private final UserService userService;

	//관리자가 '유저들'을 조회
	@GetMapping("/users")
	public ApiResult<List<UserResponse.AdminUserSearch>> adminUserSearch(
	){
		var response = userService.adminUserSearch();

		return ApiResult.ok(response);
	}

	@GetMapping("/users/{id}")
	public ApiResult<UserResponse.AdminUserDetail> adminUserDetail(
		@PathVariable Long id
	){
		var response = userService.adminUserDetail(id);
		return ApiResult.ok(response);
	}

	@PutMapping("/user/{id}")
	public ApiResult<Void> adminUserUpdate(
		@PathVariable Long id
	){
		userService.adminUserUpdate(id);
		return ApiResult.ok();
	}

	@PutMapping("/users/{id}/in-activate")
	public ApiResult<Void> adminUserInActivate(
		@PathVariable Long id
	){
		userService.adminUserInActivate(id);
		return ApiResult.ok();
	}

	//비밀번호 초기화
	@PatchMapping("/users/{id}/init-password")
	public ApiResult<Void> adminUserInitPassword(
		@PathVariable Long id
	){
		userService.adminUserInitPassword(id);
		return ApiResult.ok();
	}

	//비밀번호 재설정
	@PatchMapping("/users/{id}/change-password")
	public ApiResult<Void> adminUserChangePassword(
		@PathVariable Long id,
		@RequestBody @Valid UserRequest.ChangePassword request
	){
		userService.adminUserChangePassword(id,request);
		return ApiResult.ok();
	}

}
