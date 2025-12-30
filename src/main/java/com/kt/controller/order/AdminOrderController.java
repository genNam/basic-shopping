package com.kt.controller.order;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.dto.order.OrderResponse;
import com.kt.security.CurrentUser;
import com.kt.service.order.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

	private final OrderService orderService;

	//관리자 주문 상세조회
	@GetMapping("/{id}")
	public ApiResult<OrderResponse.AdminDetail> adminDetail(
		@AuthenticationPrincipal CurrentUser currentUser, //관리자가 맞는지 검증?
		@PathVariable Long id
	){
		var response = orderService.adminDetail(currentUser.getId(), id);

		return ApiResult.ok(response);
	}
	//관리자 주문 리스트 조회
	//관리자 주문 상태 변경
	//관리자 주문 취소
}
