package com.kt.controller.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.dto.product.ProductRequest;
import com.kt.dto.product.ProductResponse;
import com.kt.service.product.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Admin-Product")
@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController extends SwaggerAssistance {

	private final ProductService productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResult<Void> create(@RequestBody @Valid ProductRequest.Create request) {
		//반환타입이 ApiResult<Void>. ApiResult 객체를 반환할거다
		productService.create( //request dto에서 이름, 가격, 수량을 가져와서 생성
			request
		);

		return ApiResult.ok(); //성공됐음을 반환
	}

	@PutMapping("/{id}")
	public ApiResult<Void> update(
		@PathVariable Long id, //url에서 id 값을 가져온 다음
		@RequestBody @Valid ProductRequest.Update request
	) {
		productService.update( //서비스에 전달(id랑 함께)
			id,
			request
		);

		return ApiResult.ok();
	}

	@DeleteMapping("/{id}")
	public ApiResult<Void> remove(@PathVariable Long id) {
		productService.delete(id);

		return ApiResult.ok();
	}

	@PatchMapping("/{id}/activate")
	public ApiResult<Void> activate(@PathVariable Long id) {
		productService.activate(id);

		return ApiResult.ok();
	}

	@PatchMapping("/{id}/in-activate")
	public ApiResult<Void> inActivate(@PathVariable Long id) {
		productService.inActivate(id);

		return ApiResult.ok();
	}

	/*
	@PatchMapping("/{id}/toggle-sold-out")
	public ApiResult<Void> toggleSoldOut(@PathVariable Long id){
		productService.soldOut(id);

		return ApiResult.ok();
	}*/

	@GetMapping //클라이언트 요청을 처리
	//관리자 리스트 조회
	public ApiResult<List<ProductResponse.AdminList>> adminList(){

		var products = productService.adminList();

		return ApiResult.ok(products);

	}

	//관리자 상세조회
	@GetMapping("/{id}")
	public ApiResult<ProductResponse.AdminDetail> adminDetail(
		@PathVariable Long id
	){
		var product = productService.adminDetail(id);

		return ApiResult.ok(product);

	}

}
