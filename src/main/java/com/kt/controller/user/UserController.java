package com.kt.controller.user;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.user.UserRequest;
import com.kt.dto.user.UserResponse;
import com.kt.security.CurrentUser;
import com.kt.service.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "User")
@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class UserController extends SwaggerAssistance {

	private final UserService userService;

	@PostMapping
	public ApiResult<Void> create(@RequestBody @Valid UserRequest.Create request){

		userService.create(request);

		return ApiResult.ok();
	}

	//내 정보 수정
	@PatchMapping("/my-info")
	public ApiResult<Void> update(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid UserRequest.Update request){

		userService.update(request,currentUser.getId());

		return ApiResult.ok();
	}

	//비밀번호 변경
	@PatchMapping("/change-password")
	public ApiResult<Void> changePassword(
		@RequestBody @Valid UserRequest.ChangePassword request,
		@AuthenticationPrincipal CurrentUser currentUser){

		userService.changePassword(request, currentUser.getId());

		return ApiResult.ok();

	}

	//회원 삭제(탈퇴)
	@DeleteMapping("/withdrawal")
	public ApiResult<Void> delete(
		@AuthenticationPrincipal CurrentUser currentUser
	){
		userService.delete(currentUser.getId());

		return ApiResult.ok();
	}

	//내 정보 조회
	@GetMapping
	public ApiResult<UserResponse.Detail> detail(
		@AuthenticationPrincipal CurrentUser currentUser
	){
		var response = userService.detail(currentUser.getId());

		return ApiResult.ok(response);
	}

	//내 주문 조회
	@GetMapping("/orders")
	public ApiResult<List<UserResponse.MyOrders>> myOrders(
		@AuthenticationPrincipal CurrentUser currentUser
	){
		var response = userService.myOrders(currentUser.getId());

		return ApiResult.ok(response);

	}

	//내 리뷰 조회
	@GetMapping("/reviews")
	public ApiResult<List<UserResponse.MyReviews>> myReviews(
		@AuthenticationPrincipal CurrentUser currentUser
	){
		var response = userService.myReviews(currentUser.getId());

		return ApiResult.ok(response);
	}

}
