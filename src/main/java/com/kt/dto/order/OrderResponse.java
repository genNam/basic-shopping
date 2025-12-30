package com.kt.dto.order;

import java.time.LocalDateTime;
import java.util.List;

import com.kt.domain.order.Order;
import com.kt.domain.order.OrderStatus;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.dto.orderproduct.OrderProductResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderResponse {

	//사용자 주문 상세 조회
	public record UserDetail(

		@NotNull
		Long totalPrice,
		@NotNull
		LocalDateTime createdAt,
		@NotNull
		OrderStatus orderStatus,
		@NotNull
		List<OrderProductResponse.UserProductDetail> userProductDetail

	) {
		public static UserDetail from(Order order) {

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
	//관리자 주문 상세 조회
	public record AdminDetail(

		@NotNull
		Long totalPrice,
		@NotNull
		LocalDateTime createdAt,
		@NotNull
		OrderStatus orderStatus,
		@NotBlank
		String receiverName,
		@NotBlank
		String receiverMobile,
		@NotBlank
		String receiverAddress,
		@NotNull
		List<OrderProduct> orderProducts
	){
		public static AdminDetail from(Order order){
			return new AdminDetail(
				order.getTotalPrice(),
				order.getCreatedAt(),
				order.getOrderStatus(),
				order.getReceiver().getReceiverName(),
				order.getReceiver().getReceiverMobile(),
				order.getReceiver().getReceiverName(),
				order.getOrderProducts()
			);
		}
	}


	//사용자 주문 리스트 조회
	public record UserList(

		@NotNull
		Long OrderId,
		@NotNull
		Long totalPrice,
		@NotNull
		LocalDateTime createdAt,
		@NotBlank
		List<OrderProductResponse.ProductNameList> productName


	){
		public static UserList from(Order order){

			var productNames = order.getOrderProducts().stream()
				.map(p -> OrderProductResponse.ProductNameList.from(p))
				.toList();

			return new UserList(
				order.getId(),
				order.getTotalPrice(),
				order.getCreatedAt(),
				productNames
			);
		}
	}

	//관리자 주문 리스트 조회
	public record AdminList(

		@NotNull
		Long OrderId,
		@NotNull
		Long totalPrice,
		@NotNull
		LocalDateTime createdAt,
		@NotBlank
		List<OrderProductResponse.ProductNameList> productName
	){
		public static AdminList from(Order order){

			var productNames = order.getOrderProducts().stream()
				.map(p -> OrderProductResponse.ProductNameList.from(p))
				.toList();

			return new AdminList(

				order.getId(),
				order.getTotalPrice(),
				order.getCreatedAt(),
				productNames

			);
		}
	}

}
