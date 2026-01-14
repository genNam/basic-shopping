package com.kt.controller.order;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.kt.common.response.ApiResult;
import com.kt.dto.order.OrderCreateRequest;
import com.kt.dto.order.OrderRequest;
import com.kt.dto.order.OrderResponse;
import com.kt.security.CurrentUser;
import com.kt.service.order.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders") //사용자는 여러 주문을 가질 수 있으니까 orders
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ApiResult<Void> create(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid OrderCreateRequest request
	){
		orderService.create(currentUser.getId(),request);

		return ApiResult.ok();

	}

	//사용자 주문 상세 조회
	@GetMapping("/{id}")
	public ApiResult<OrderResponse.UserDetail> userDetail(
		@AuthenticationPrincipal CurrentUser currentUser,
		@PathVariable Long id
	){
		var detail = orderService.userDetail(currentUser.getId(),id);

		return ApiResult.ok(detail);
	}

	//사용자 주문 리스트 조회
	@GetMapping
	public ApiResult<List<OrderResponse.UserList>> userList(
		@AuthenticationPrincipal CurrentUser currentUser
	){
		var orderList = orderService.userList(currentUser.getId());

		return ApiResult.ok(orderList);
	}

	//사용자 주문 수정
	@PutMapping("/{id}")
	public ApiResult<Void> userUpdate(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid OrderRequest.UserUpdate request,
		@PathVariable Long id
	){
		orderService.userUpdate(currentUser.getId(),request,id);

		return ApiResult.ok();
	}

	//사용자 주문 취소
	@DeleteMapping(value = "/{id}/cancel")
	public ApiResult<Void> userCancel(
		@AuthenticationPrincipal CurrentUser currentUser,
		@PathVariable Long id
	){
		orderService.userCancel(currentUser.getId(),id);

		return ApiResult.ok();
	}
}
