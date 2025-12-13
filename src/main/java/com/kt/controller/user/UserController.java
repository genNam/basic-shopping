package com.kt.controller.user;

import org.springframework.web.bind.annotation.*;

import com.kt.common.response.ApiResult;
import com.kt.dto.user.UserRequest;
import com.kt.service.user.UserService;

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

	@PatchMapping("/my-info/{id}") //todo : 인증 기능 구현 추가
	public ApiResult<Void> update(
		@PathVariable Long id,
		@RequestBody @Valid UserRequest.Update request){

		userService.update(request,id);

		return ApiResult.ok();
	}
}
