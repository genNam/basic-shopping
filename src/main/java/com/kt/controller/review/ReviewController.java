package com.kt.controller.review;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.dto.review.ReviewRequest;
import com.kt.dto.review.ReviewResponse;
import com.kt.security.CurrentUser;
import com.kt.service.review.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	//상품 리뷰 작성(사용자)
	@PostMapping("/reviews")
	public ApiResult<Void> create(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid ReviewRequest.Create request
	){
		reviewService.create(currentUser.getId(),request);
		return ApiResult.ok();
	}

	//상품 리뷰 조회(사용자)
	@PostMapping("/reviews?productId")
	public ApiResult<List<ReviewResponse.UserSearch>> userSearch(
		@PathVariable Long productId
	){
		var result = reviewService.userSearch(productId);
		return ApiResult.ok(result);
	}

	//상품 리뷰 수정(사용자)
	//reviews/{id}
	@PutMapping("/reviews/{id}")
	public ApiResult<ReviewResponse.UserUpdate> userUpdate(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid ReviewRequest.Update request
	){
		var result = reviewService.userUpdate(currentUser.getId(), request);

		return ApiResult.ok(result);
	}
	//상품 리뷰삭제(사용자)
	//reviews/{id}


	//상품리뷰조회(관리자)
	//admin/reviews
	//상품리뷰삭제(관리자)
	//admin/reviews/{id}

}
