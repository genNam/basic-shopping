package com.kt.repository.orderproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kt.domain.orderproduct.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
}
//상품등록 -> 그 상품이 있는지 확인
//사용자 -> 상품 주문 -> 그 상품이 있는