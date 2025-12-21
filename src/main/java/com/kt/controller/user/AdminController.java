package com.kt.controller.user;

import org.springframework.web.bind.annotation.*;

import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.user.UserRequest;
import com.kt.service.user.AdminService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Admin")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController extends SwaggerAssistance {

	private final AdminService adminUserService;

	@PostMapping
	public ApiResult<Void> create(
		@RequestBody @Valid UserRequest.Create request
	){
		adminUserService.create(request);

		return ApiResult.ok();
	}


}
