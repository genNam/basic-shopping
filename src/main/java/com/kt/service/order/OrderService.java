package com.kt.service.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.common.support.Preconditions;
import com.kt.domain.order.Order;
import com.kt.domain.order.Receiver;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.dto.order.OrderCreateRequest;
import com.kt.dto.order.OrderRequest;
import com.kt.dto.order.OrderResponse;
import com.kt.dto.orderproduct.OrderProductResponse;
import com.kt.repository.order.OrderRepository;
import com.kt.repository.orderproduct.OrderProductRepository;
import com.kt.repository.product.ProductRepository;
import com.kt.repository.user.UserRepository;
import com.kt.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderProductRepository orderProductRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	//주문 생성
	public void create(Long userId, OrderCreateRequest request) {

		//상품이 있는지
		var product = productRepository.findById(request.productId())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

		//수량이 0 이상인지
		Preconditions.validate(request.quantity() > 0, ErrorCode.INVALID_ORDER_QUANTITY);

		//유저 조회
		var user = userRepository.findById(userId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		var receiver = new Receiver(
			request.receiverName(),
			request.receiverAddress(),
			request.receiverMobile()
		);

		//주문 생성
		var order = Order.create(user, receiver);

		//주문 상품 생성
		var orderProduct = new OrderProduct(order, product, request.quantity(), request.price());

		//연관관계
		product.mapToOrderProduct(orderProduct);
		order.mapToOrder(orderProduct);

		//재고감소

		//저장
		orderRepository.save(order);
		orderProductRepository.save(orderProduct);

	}

	//사용자 주문 상세 조회
	public OrderResponse.UserDetail userDetail(Long userId, Long orderId) {

		//주문이 내 주문이(userId) 맞는지
		var order = orderRepository.findByIdAndUserId(userId, orderId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));

		return OrderResponse.UserDetail.from(order);
	}

	//관리자 주문 상세 조회
	public OrderResponse.AdminDetail adminDetail(Long adminId, Long orderId){

		var order = orderRepository.findById(orderId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));

		return OrderResponse.AdminDetail.from(order);
	}


	//사용자 주문 리스트 조회
	public List<OrderResponse.UserList> userList(Long userId){

		//주문이 내 주문들이 맞는지 확인
		var orders = orderRepository.findAllByUserId(userId);

		var response = orders.stream()
			.map(order-> OrderResponse.UserList.from(order))
			.toList();

		return response;
	}

	//사용자 주문 수정
	public void userUpdate(Long userId, OrderRequest.userUpdate request, Long orderId){

		//주문이 내 주문이 맞는지 확인
		var order = orderRepository.findByIdAndUserId(userId, orderId)
				.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));

		order.update(
			new Receiver(
				request.receiverName(),
				request.receiverMobile(),
				request.receiverAddress()
			)
		);
	}

	//사용자 주문 취소
	public void userCancel(Long userId, Long orderId){

		//주문이 내 주문이 맞는지 확인
		var order = orderRepository.findByIdAndUserId(userId, orderId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));

		order.cancel();

	}

}
