package com.kt.domain.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.kt.common.exception.ErrorCode;
import com.kt.common.support.BaseEntity;
import com.kt.common.support.Preconditions;
import com.kt.domain.orderproduct.OrderProduct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {

	//베이스엔티티를 상속받음으로써, 주키/누가/언제 만들었는지/수정했는지도 상속받음

	private String name; //칼럼
	private Long price;
	private Long stock;

	@Enumerated(EnumType.STRING)
	private ProductStatus status = ProductStatus.ACTIVATED;

	@OneToMany(mappedBy = "product")
	private List<OrderProduct> orderproducts = new ArrayList<>(); //주문한 상품들을 받음

	public Product(String name, Long price, Long stock) {

		Preconditions.validate(Strings.isNotBlank(name), ErrorCode.INVALID_PARAMETER);
		Preconditions.validate(price != null && price >= 0, ErrorCode.INVALID_PARAMETER);
		Preconditions.validate(stock >= 0, ErrorCode.INVALID_PARAMETER);

		this.name = name;
		this.price = price;
		this.stock = stock;

	}

	public void mapToOrderProduct(OrderProduct orderProduct) {
		this.orderproducts.add(orderProduct); //주문한 상품들에 대한 객체를 가져와서 리스트에 저장
	}
}
