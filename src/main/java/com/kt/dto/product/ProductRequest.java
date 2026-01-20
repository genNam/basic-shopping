package com.kt.dto.product;

import java.util.List;

import com.kt.domain.product.Product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductRequest {

	@Getter
	@AllArgsConstructor
	@Schema(name = "ProductRequest.Create") //api 문서에서 설명하는데 사용
	public static class Create {
		@NotBlank
		private String name;
		@NotNull
		private Long price;
		@NotNull
		private Long quantity;
	}

	@Getter
	@AllArgsConstructor
	@Schema(name = "ProductRequest.Update") //이름, 가격, 수량을 json으로 받고
	public static class Update {
		@NotBlank
		private String name;
		@NotNull
		private Long price;
		@NotNull
		private Long quantity;
	}

	@Getter
	@AllArgsConstructor
	@Schema(name = "ProductRequest.AdminSoldOutProducts")
	public static class AdminSoldOutProducts {

		@NotBlank
		List<Product> productList;

	}
}
