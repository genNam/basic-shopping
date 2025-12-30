package com.kt.dto.order;

import com.kt.domain.order.OrderStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderRequest {

	public record UserUpdate(

		@NotBlank
		String receiverName,
		@NotBlank
		String receiverMobile,
		@NotBlank
		String receiverAddress

	){

	}

	//주문 상태 변경
	public record ChangeStatus(

		@NotNull
		OrderStatus orderStatus
	){

	}
}
