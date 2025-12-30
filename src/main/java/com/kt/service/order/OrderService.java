package com.kt.service.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.common.support.Preconditions;
import com.kt.domain.order.Order;
import com.kt.domain.order.Receiver;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.dto.order.OrderCreateRequest;
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
	private final ProductService productService;

	public void create(Long userId, OrderCreateRequest request){

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
		var orderProduct = new OrderProduct(order,product,request.quantity(), request.price());

		//연관관계
		product.mapToOrderProduct(orderProduct);
		order.mapToOrder(orderProduct);

		//재고감소

		//저장
		orderRepository.save(order);
		orderProductRepository.save(orderProduct);

	}
}
