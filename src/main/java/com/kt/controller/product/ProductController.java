package com.kt.controller.product;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.response.ApiResult;
import com.kt.common.support.SwaggerAssistance;
import com.kt.domain.product.Product;
import com.kt.dto.product.ProductResponse;
import com.kt.security.CurrentUser;
import com.kt.service.product.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Product")
@RestController //json 데이터를 반환하는 컨트롤러
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController extends SwaggerAssistance {

	private final ProductService productService;

	//사용자 상품 리스트 조회
	@GetMapping
	public ApiResult<List<ProductResponse.UserList>> userList(){

		var product = productService.getUserList();

		return ApiResult.ok(product);
	}

	//사용자 상품 상세 조회
	@GetMapping("/{id}")
	public ApiResult<ProductResponse.UserDetail> userDetail(@PathVariable Long id){

		var product = productService.getUserDetail(id);

		return ApiResult.ok(product);


	}

	//사용자 상품 리뷰 조회
	@GetMapping("/{id}/reviews")
	public ApiResult<List<ProductResponse.ProductReview>> productReviews(
		@PathVariable Long id
	){
		var response = productService.productReviews(id);

		return ApiResult.ok(response);

	}
}
