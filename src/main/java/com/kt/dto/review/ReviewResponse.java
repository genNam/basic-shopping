package com.kt.dto.review;

import java.time.LocalDateTime;

import com.kt.domain.review.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewResponse {

	//상품에 대한 리뷰(사용자 조회)
	public record UserSearch(

		//상품
		@NotNull
		Long productId,

		@NotBlank
		String productName,

		//작성자 이름
		String userName,

		//작성 날짜
		LocalDateTime createdAt,

		//리뷰 내용
		String content

	){
		public static UserSearch of(Review review){

			return new UserSearch(
				review.getProduct().getId(),
				review.getProduct().getName(),
				review.getUser().getName(),
				review.getCreatedAt(),
				review.getContent()

			);

		}

	}
}
