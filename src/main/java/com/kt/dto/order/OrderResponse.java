package com.kt.dto.order;

import java.time.LocalDateTime;
import java.util.List;

import com.kt.domain.order.Order;
import com.kt.domain.order.OrderStatus;
import com.kt.dto.orderproduct.OrderProductResponse;

public class OrderResponse {

	public record UserDetail(

		Long totalPrice,
		LocalDateTime createdAt,
		OrderStatus orderStatus,
		List<OrderProductResponse.UserProductDetail> userProductDetails

	) {
		public static UserDetail of(Order order) {

			var userProductDetails = order.getOrderProducts().stream()
				.map(p -> OrderProductResponse.UserProductDetail.from(p))
				.toList();

			return new UserDetail(
				order.getTotalPrice(),
				order.getCreatedAt(),
				order.getOrderStatus(),
				userProductDetails
			);

		}
	}

}
