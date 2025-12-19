package com.kt.controller.user;

import org.springframework.web.bind.annotation.*;

import com.kt.common.response.ApiResult;
import com.kt.dto.user.UserRequest;
import com.kt.service.user.AdminUserService;
import com.kt.service.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Admin")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {

	private final AdminUserService adminUserService;

	@PostMapping
	public ApiResult<Void> create(
		@RequestBody @Valid UserRequest.Create request
	){
		adminUserService.create(request);

		return ApiResult.ok();
	}


}
