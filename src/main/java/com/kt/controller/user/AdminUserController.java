package com.kt.controller.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.user.UserResponse;
import com.kt.service.user.AdminService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Admin-User")
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController extends SwaggerAssistance {

	private final AdminService adminService;

	//관리자가 '유저들'을 조회
	@GetMapping("/users")
	public ApiResult<List<UserResponse.AdminUserSearch>> adminUserSearch(
	){
		var response = adminService.adminUserSearch();

		return ApiResult.ok(response);
	}

	@GetMapping("/users/{id}")
	public ApiResult<UserResponse.AdminUserDetail> adminUserDetail(
		@PathVariable Long id
	){
		var response = adminService.adminUserDetail(id);
		return ApiResult.ok(response);
	}

	@PutMapping("/user/{id}")
	public ApiResult<Void> adminUserUpdate(
		@PathVariable Long id
	){
		adminService.adminUserUpdate(id);
		return ApiResult.ok();
	}


	//회원 비활성화 /users/{id}/in-activate
	//회원 비밀번호 초기화 users/{id}/init-password
	//회원 비밀번호 변경 {id}/change-password

}
