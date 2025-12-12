package com.kt.dto.product;

import java.time.LocalDateTime;

import com.kt.domain.product.Product;
import com.kt.domain.product.ProductStatus;

public class ProductResponse {

	//관리자 리스트 조회
	public record AdminSearch(
		String name,
		Long price,
		Long quantity
	){
		public static AdminSearch from(Product product){

			return new AdminSearch(
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




}
