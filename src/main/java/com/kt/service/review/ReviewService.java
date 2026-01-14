package com.kt.service.review;

import static com.kt.common.exception.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.common.support.Preconditions;
import com.kt.domain.review.Review;
import com.kt.dto.review.ReviewRequest;
import com.kt.dto.review.ReviewResponse;
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

	public void create(Long userId, ReviewRequest.Create request) {

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

	//사용자 상품 리뷰 조회
	public List<ReviewResponse.UserSearch> userSearch(Long productId) {

		//해당 상품의 리뷰들
		var reviewList = reviewRepository.findByProductId(productId);

		//응답 생성
		var response = reviewList.stream()
			.map(review -> ReviewResponse.UserSearch.of(review))
			.toList();

		return response;
	}

	//사용자 리뷰 수정
	public ReviewResponse.UserUpdate userUpdate(Long reviewId, Long userId, ReviewRequest.Update request) {


		//유저,상품과 일치하는 리뷰 가져오기
		//리뷰 id만으로도 unique 하므로 product에 대한 정보 필요x
		var review = reviewRepository.findByIdAndUserId(reviewId,userId)
			.orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND));

		//수정하기
		review.update(request.content());

		//응답 객체 만들기
		var response = ReviewResponse.UserUpdate.of(review);

		return response;
	}

	//사용자 리뷰 삭제
	public void delete(Long userId, Long reviewId){

		//유저와 일치하는 리뷰 찾기
		var review = reviewRepository.findByIdAndUserId(reviewId,userId)
			.orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND));

		//삭제하기
		reviewRepository.delete(review);

	}

	//관리자 리뷰 조회
	public List<ReviewResponse.AdminSearch> adminSearch(Long adminId){

		//리뷰 조히
		var reviews = reviewRepository.findAll();

		//응답 객체 생성
		var response = reviews.stream()
			.map(review -> ReviewResponse.AdminSearch.of(review))
			.toList();

		return response;

	}
	//관리자 리뷰 삭제
	public void adminDelete(Long adminId, Long reviewId){

		//해당하는 리뷰가 있는지 확인
		var review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND));

		reviewRepository.delete(review);
	}

}