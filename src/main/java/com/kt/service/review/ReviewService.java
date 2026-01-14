package com.kt.service.review;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.domain.review.Review;
import com.kt.dto.review.ReviewRequest;
import com.kt.repository.orderproduct.OrderProductRepository;
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

	public void create(Long userId, ReviewRequest.Create request){

		//userId에 해당하는 user 객체를 가져옴
		var user = userRepository.findById(userId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		//주문한 상품
		var orderProduct = orderProductRepository.findById(request.orderProductId())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER_PRODUCT));

		//리뷰 생성
		var review = Review.create(request.content(), user, orderProduct);

		//저장
		reviewRepository.save(review);

	}

}
