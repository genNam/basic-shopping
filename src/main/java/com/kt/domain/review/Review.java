package com.kt.domain.review;

import com.kt.common.support.BaseEntity;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.domain.product.Product;
import com.kt.domain.user.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "reviews")
@NoArgsConstructor
public class Review extends BaseEntity {

	@OneToOne
	private OrderProduct orderProduct; //주문한 상품인지 확인

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product; //어떤 상품에 대한 리뷰인지

	@Column
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Review(String content, User user, OrderProduct orderProduct, Product product){
		this.user = user;
		this.orderProduct = orderProduct;
		this.product = product;
		this.content = content;
		this.createdAt = getCreatedAt();
		this.updatedAt = getUpdatedAt();
	}

	//리뷰 작성
	public static Review create(String content, User user, OrderProduct orderProduct,
		Product product){

		return new Review(
			content,
			user,
			orderProduct,
			product
		);
	}


}
