package com.kt.controller.review;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.dto.review.ReviewRequest;
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
	//reviews?productId
	//상품 리뷰 수정(사용자)
	//reviews/{id}
	//상품 리뷰삭제(사용자)
	//reviews/{id}
	//상품리뷰조회(관리자)
	//admin/reviews
	//상품리뷰삭제(관리자)
	//admin/reviews/{id}

}
