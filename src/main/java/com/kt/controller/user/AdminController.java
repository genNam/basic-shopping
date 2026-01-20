package com.kt.controller.user;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.user.UserRequest;
import com.kt.dto.user.UserResponse;
import com.kt.service.user.AdminService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Admin")
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController extends SwaggerAssistance {

	private final AdminService adminService;

	@PostMapping
	public ApiResult<Void> createAdmin(@RequestBody @Valid UserRequest.CreateAdmin request){

		adminService.createAdmin(request);

		return ApiResult.ok();
	}

	//관리자의 관리자 조회
	@GetMapping("/admins")
	public ApiResult<List<UserResponse.AdminUserSearch>> adminsSearch(
	){
		var response = adminService.adminsSearch();
		return ApiResult.ok(response);
	}

	//관리자의 관리자 상세 조회
	@GetMapping("/admins/{id}")
	public ApiResult<UserResponse.AdminUserDetail> adminDetail(
		@PathVariable Long id
	){
		var response = adminService.adminDetail(id);
		return ApiResult.ok(response);
	}
	// /admins/{id}
	//관리자의 관리자 수정
	// /admins/{id}
	//관리자의 관리자 비번 초기화
	// /admins/{id}/init-password


}
