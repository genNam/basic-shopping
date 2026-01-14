package com.kt.dto.review;


import jakarta.validation.constraints.*;

public class ReviewRequest {

	public record Create(

		@NotBlank(message = "내용은 한 글자 이상 입력해주세요.")
		String content,

		@NotNull(message = "구매한 상품들에 대해서만 리뷰를 작성할 수 있습니다.")
		Long orderProductId,

		@NotNull
		Long productId

	){

	}

	//리뷰 수정
	public record Update(

		@NotBlank
		String content,

		@NotNull
		Long productId,

		@NotNull
		Long orderProductId

	){

	}
}
