package com.kt.controller.product;

import org.springframework.http.HttpStatus;
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
import com.kt.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Admin")
@Tag(name = "Product")
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
			request.getName(),
			request.getPrice(),
			request.getQuantity()
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
			request.getName(),
			request.getPrice(),
			request.getQuantity()
		);

		return ApiResult.ok();
	}
}
