package com.kt.controller.order;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.dto.order.OrderRequest;
import com.kt.dto.order.OrderResponse;
import com.kt.security.CurrentUser;
import com.kt.service.order.OrderService;

import jakarta.validation.Valid;
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
	@GetMapping
	public ApiResult<List<OrderResponse.AdminList>> adminList(
		@AuthenticationPrincipal CurrentUser currentUser
	){
		var response = orderService.adminList(currentUser.getId());

		return ApiResult.ok(response);
	}

	//관리자 주문 상태 변경
	@PutMapping("/{id}/change-status")
	public ApiResult<Void> adminStatusChange(
		@AuthenticationPrincipal CurrentUser currentUser,
		@RequestBody @Valid OrderRequest.ChangeStatus request,
		@PathVariable Long id
	){
		orderService.adminChangeStatus(currentUser.getId(), request, id);

		return ApiResult.ok();
	}

	//관리자 주문 취소
	@DeleteMapping("/{id}/cancel")
	public ApiResult<Void> adminCancel(
		@AuthenticationPrincipal CurrentUser currentUser,
		@PathVariable Long id
	){
		orderService.adminCancel(currentUser.getId(),id);

		return ApiResult.ok();
	}
}
