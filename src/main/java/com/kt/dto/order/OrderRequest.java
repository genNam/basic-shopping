package com.kt.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderRequest {

	public record userUpdate(

		@NotBlank
		String receiverName,
		@NotBlank
		String receiverMobile,
		@NotBlank
		String receiverAddress

	){

	}
}
