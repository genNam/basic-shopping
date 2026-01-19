package com.kt.dto.user;

import java.time.LocalDateTime;

import com.kt.domain.order.Order;
import com.kt.domain.order.OrderStatus;
import com.kt.domain.review.Review;
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

	//사용자의 내 리뷰 조회
	public record MyReviews(

		Long productId,
		Long reviewId,
		LocalDateTime createdAt,
		String content

	){
		public static MyReviews from(Review review){

			return new MyReviews(
				review.getProduct().getId(),
				review.getId(),
				review.getCreatedAt(),
				review.getContent()
			);

		}

	}

	//관리자의 회원 조회
	public record AdminUserSearch(

		Long userId,
		String userName,
		String mobile,
		LocalDateTime createdAt

	){
		public static AdminUserSearch from(User user){
			return new AdminUserSearch(

				user.getId(),
				user.getName(),
				user.getMobile(),
				user.getCreatedAt()

			);
		}
	}

	//관리자의 회원 상세 조회
	public record AdminUserDetail(

		Long userId,
		String loginId,
		String userName,
		String mobile,
		String email

	){
		public static AdminUserDetail from(User user){
			return new AdminUserDetail(
				user.getId(),
				user.getLoginId(),
				user.getName(),
				user.getMobile(),
				user.getEmail()

			);
		}
	}

}
