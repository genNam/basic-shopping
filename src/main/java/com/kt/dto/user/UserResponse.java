package com.kt.dto.user;

import java.time.LocalDateTime;

import com.kt.domain.order.Order;
import com.kt.domain.order.OrderStatus;
import com.kt.domain.user.User;

public class UserResponse {

	public record Detail(

		String id,
		String name,
		String email,
		LocalDateTime createdAt

	){
		public static Detail from(User user){

			return new Detail(
				user.getLoginId(),
				user.getName(),
				user.getEmail(),
				user.getCreatedAt()
			);
		}
	}

	//사용자의 내 주문 조회
	public record MyOrders(

		Long orderId,
		Long totalPrice,
		LocalDateTime createdAt,
		OrderStatus orderStatus
	){
		public static MyOrders from(Order order){

			return new MyOrders(

				order.getId(),
				order.getTotalPrice(),
				order.getCreatedAt(),
				order.getOrderStatus()

			);
		}
	}

}
