package com.kt.dto.order;

import jakarta.validation.constraints.*;

public record OrderCreateRequest(

		@NotNull
		Long productId,

		@NotNull
		Long price,

		@NotNull
		@Min(1)
		Long quantity,

		@NotBlank
		String receiverName,

		@NotBlank
		String receiverMobile,

		@NotBlank
		String receiverAddress
) {

}