package com.kt.domain.orderproduct;

import com.kt.common.support.BaseEntity;
import com.kt.domain.order.Order;
import com.kt.domain.product.Product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderProduct extends BaseEntity {

	@Column
	private Long quantity;

	//주문 시점의 가격
	@Column
	private Long price;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public OrderProduct(Order order, Product product, Long quantity, Long price){
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
}

