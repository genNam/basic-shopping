package com.kt.dto.product;

import com.kt.domain.product.Product;

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




}
