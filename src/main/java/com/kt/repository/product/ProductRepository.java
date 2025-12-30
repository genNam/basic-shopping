package com.kt.repository.product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

	//단순조회
	default Product findByIdOrThrow(Long id){
		return findById(id).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
	}
}
