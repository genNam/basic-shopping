package com.kt.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kt.common.exception.ErrorCode;
import com.kt.common.support.Preconditions;
import com.kt.domain.user.User;
import com.kt.dto.user.UserRequest;
import com.kt.dto.user.UserResponse;
import com.kt.repository.order.OrderRepository;
import com.kt.repository.review.ReviewRepository;
import com.kt.repository.user.UserRepository;

//트랜잭션 어노테이션
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final ReviewRepository reviewRepository;
	private final PasswordEncoder passwordEncoder;

	public void create(UserRequest.Create request){

		userRepository.save(
			User.create(
				request.loginId(),
				passwordEncoder.encode(request.password()),
				request.name(),
				request.email(),
				request.mobile(),
				request.gender(),
				request.birthday(),
				LocalDateTime.now(),
				LocalDateTime.now()
			)
		);
	}

	public void update(UserRequest.Update request, Long id){

		var user = userRepository.findByIdOrThrow(id);

		user.update(
			request.name(), //record를 사용했기 때문에 getName()과 같은 형식으로 작성하지 않아도 됨
			request.email(),
			request.mobile()
		);
	}

	//비밀번호 수정
	public void changePassword(UserRequest.ChangePassword request, Long id){

		var user = userRepository.findByIdOrThrow(id);

		//비밀번호가 다르면 예외처리
		Preconditions.validate(user.getPassword().equals(request.newPassword()),
			ErrorCode.DOES_NOT_MATCH_OLD_PASSWORD);
		//지금 비밀번호랑 새로운 비밀번호랑 같으면 예외처리
		Preconditions.validate(!request.oldPassword().equals(request.newPassword()),
			ErrorCode.CAN_NOT_ALLOWED_SAME_PASSWORD);


		user.changePassword(request.newPassword());

	}

	//삭제
	public void delete(Long id){

		userRepository.deleteById(id);
	}

	//내 정보 조회
	//조회용 메서드를 만들때 사용 -> 성능적으로 더 이점이 있음
	@Transactional(readOnly = true)
	public UserResponse.Detail detail(Long id){

		var user = userRepository.findByIdOrThrow(id);
		var response = UserResponse.Detail.from(user);

		return response;
	}

	//내 주문 조회
	@Transactional(readOnly = true)
	public List<UserResponse.MyOrders> myOrders(Long userId){

		var orderList = orderRepository.findByUserId(userId);
		var response = orderList.stream()
			.map(o -> UserResponse.MyOrders.from(o))
			.toList();

		return response;
	}

	//내 리뷰 조회
	@Transactional(readOnly = true)
	public List<UserResponse.MyReviews> myReviews(Long userId){

		var reviewList = reviewRepository.findByUserId(userId);
		var response = reviewList.stream()
			.map(r->UserResponse.MyReviews.from(r))
			.toList();

		return response;
	}


}
