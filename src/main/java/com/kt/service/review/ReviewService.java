package com.kt.service.review;

import static com.kt.common.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.common.support.Preconditions;
import com.kt.domain.review.Review;
import com.kt.dto.review.ReviewRequest;
import com.kt.repository.orderproduct.OrderProductRepository;
import com.kt.repository.product.ProductRepository;
import com.kt.repository.review.ReviewRepository;
import com.kt.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final OrderProductRepository orderProductRepository;
	private final ProductRepository productRepository;

	public void create(Long userId, ReviewRequest.Create request){

		//userId에 해당하는 user 객체를 가져옴
		var user = userRepository.findById(userId)
			.orElseThrow(() -> new CustomException(NOT_FOUND_USER));

		//상품
		var product = productRepository.findById(request.productId())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

		//주문한 상품
		var orderProduct = orderProductRepository.findById(request.orderProductId())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER_PRODUCT));

		//상품과 주문한 상품이 일치하는지
		Preconditions.validate(request.productId().equals(request.orderProductId()), ORDER_PRODUCT_MISMATCH);

		//리뷰 생성
		var review = Review.create(request.content(), user, orderProduct, product);

		//저장
		reviewRepository.save(review);

	}

	/*
	//사용자 상품 리뷰 조회
	public ReviewResponse.UserSearch userSearch(Long userId, Long productId){

		//해당 상품의 리뷰 찾기
		var reviewList = reviewRepository.


		return
	}*/

}
