package com.kt.controller.review;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.dto.review.ReviewResponse;
import com.kt.security.CurrentUser;
import com.kt.service.review.ReviewService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Admin-Review")
@RestController
@RequestMapping("/admin/reviews")
@RequiredArgsConstructor
public class AdminReviewController {

	private final ReviewService reviewService;

	//상품리뷰조회(관리자)
	@GetMapping
	public ApiResult<List<ReviewResponse.AdminSearch>> adminSearch(
		@AuthenticationPrincipal CurrentUser currentUser
	){
		var result = reviewService.adminSearch(currentUser.getId());
		return ApiResult.ok(result);
	}

	//상품리뷰 상세 삭제(관리자)
	@DeleteMapping("/{id}")
	public ApiResult<Void> adminDelete(
		@AuthenticationPrincipal CurrentUser currentUser,
		@PathVariable Long id
	){
		reviewService.adminDelete(currentUser.getId(),id);
		return ApiResult.ok();
	}

}
