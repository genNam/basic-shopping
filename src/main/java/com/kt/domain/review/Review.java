package com.kt.domain.review;

import com.kt.common.support.BaseEntity;
import com.kt.domain.orderproduct.OrderProduct;
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
	private OrderProduct orderProduct;

	@Column
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Review(String content, User user, OrderProduct orderProduct){
		this.user = user;
		this.orderProduct = orderProduct;
		this.content = content;
		this.createdAt = getCreatedAt();
		this.updatedAt = getUpdatedAt();
	}

	//리뷰 작성
	public static Review create(String content, User user, OrderProduct orderProduct){

		return new Review(
			content,
			user,
			orderProduct
		);
	}


}
