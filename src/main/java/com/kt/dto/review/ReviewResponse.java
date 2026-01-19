package com.kt.dto.review;

import java.time.LocalDateTime;

import com.kt.domain.review.Review;

public class ReviewResponse {

	//상품에 대한 리뷰(사용자 조회)
	public record UserSearch(

		//상품
		Long productId,

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

	//리뷰 수정(사용자)
	public record UserUpdate(

		//상품
		Long productId,

		String productName,

		//작성자 이름
		String userName,

		//수정 날짜
		LocalDateTime updateAt,

		//리뷰 내용
		String content

	){
		public static UserUpdate of(Review review){

			return new UserUpdate(

				review.getProduct().getId(),
				review.getProduct().getName(),
				review.getUser().getName(),
				review.getUpdatedAt(),
				review.getContent()

			);
		}

	}

	//관리자 리뷰 조회
	public record AdminSearch(

		//상품
		Long productId,

		String productName,

		//작성자 이름
		String userName,

		//리뷰 내용
		String content

	){
		public static AdminSearch of(Review review){

			return new AdminSearch(

				review.getProduct().getId(),
				review.getProduct().getName(),
				review.getUser().getName(),
				review.getContent()

			);
		}

	}
}
