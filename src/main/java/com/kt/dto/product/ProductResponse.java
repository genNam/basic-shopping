package com.kt.dto.product;

import java.time.LocalDateTime;

import com.kt.domain.product.Product;
import com.kt.domain.product.ProductStatus;
import com.kt.domain.review.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductResponse {

	//관리자 리스트 조회
	public record AdminList(
		@NotBlank
		String name,
		@NotNull
		Long price,
		@NotNull
		Long quantity
	){
		public static AdminList from(Product product){

			return new AdminList(
				product.getName(),
				product.getPrice(),
				product.getStock()
			);

		}

	}

	//관리자 상세 조회
	public record AdminDetail(

		@NotNull
		Long id,
		@NotBlank
		String name,
		@NotNull
		Long price,
		@NotNull
		Long quantity,
		@NotNull
		LocalDateTime createdAt,
		@NotNull
		ProductStatus status

	){
		public static AdminDetail from(Product product){

			return new AdminDetail(
				product.getId(),
				product.getName(),
				product.getPrice(),
				product.getStock(),
				product.getCreatedAt(),
				product.getStatus()
			);
		}
	}

	//사용자 리스트 조회
	public record UserList(
		@NotBlank
		String name,
		@NotNull
		Long price
	){
		public static UserList from(Product product){

			return new UserList(
				product.getName(),
				product.getPrice()
			);
		}
	}

	//사용자 상세 조회
	public record UserDetail(

		@NotBlank
		String name,
		@NotNull
		Long price,
		boolean status

	){
		public static UserDetail of(Product product, boolean status){

			return new UserDetail(

				product.getName(),
				product.getPrice(),
				status
			);
		}
	}

	//사용자 상품 리뷰 조회
	public record ProductReview(

		Long productId,
		Long reviewId,
		LocalDateTime createdAt,
		String content

	){
		public static ProductReview from(Review review){

			return new ProductReview(

				review.getProduct().getId(),
				review.getId(),
				review.getCreatedAt(),
				review.getContent()

			);
		}
	}
}
