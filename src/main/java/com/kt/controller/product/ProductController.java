package com.kt.controller.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.support.SwaggerAssistance;
import com.kt.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User")
@Tag(name = "Product")
@RestController //json 데이터를 반환하는 컨트롤러
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController extends SwaggerAssistance {

	private final ProductService productService;
}
