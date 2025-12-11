package com.kt.dto.product;

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
}
