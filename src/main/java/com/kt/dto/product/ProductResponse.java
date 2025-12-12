package com.kt.dto.product;

import java.time.LocalDateTime;

import com.kt.domain.product.Product;
import com.kt.domain.product.ProductStatus;

public class ProductResponse {

	//관리자 리스트 조회
	public record AdminList(
		String name,
		Long price,
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

		Long id,
		String name,
		Long price,
		Long quantity,
		LocalDateTime createdAt,
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

		String name,
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

		String name,
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




}
