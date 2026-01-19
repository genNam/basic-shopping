package com.kt.controller.review;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.dto.review.ReviewRequest;
import com.kt.dto.review.ReviewResponse;
import com.kt.security.CurrentUser;
import com.kt.service.review.ReviewService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Review")
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	//상품 리뷰 작성(사용자)
	@PostMapping
	public ApiResult<Void> create(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid ReviewRequest.Create request
	){
		reviewService.create(currentUser.getId(),request);
		return ApiResult.ok();
	}

	//상품 리뷰 조회(사용자)
	@GetMapping
	public ApiResult<List<ReviewResponse.UserSearch>> userSearch(
		@RequestParam Long productId
	){
		var result = reviewService.userSearch(productId);
		return ApiResult.ok(result);
	}

	//상품 리뷰 수정(사용자)
	@PutMapping("/{id}")
	public ApiResult<ReviewResponse.UserUpdate> userUpdate(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid ReviewRequest.Update request,
		@PathVariable Long id
	){
		var result = reviewService.userUpdate(id, currentUser.getId(), request);

		return ApiResult.ok(result);
	}

	//상품 리뷰삭제(사용자)
	@DeleteMapping("/{id}")
	public ApiResult<Void> delete(
		@AuthenticationPrincipal CurrentUser currentUser,
		@PathVariable Long id
	){
		reviewService.delete(currentUser.getId(),id);

		return ApiResult.ok();
	}

}
