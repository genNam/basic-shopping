package com.kt.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.kt.common.support.BaseEntity;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.domain.user.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

	//상품에 대한 정보
	@OneToMany(mappedBy = "order")
	private List<OrderProduct> orderProducts = new ArrayList<>();

	//사용자에 대한 정보
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	//배송받는 사람
	@Embedded
	private Receiver receiver;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Column
	private Long totalPrice;


	private Order(User user, Receiver receiver){

		this.user = user;
		this.receiver = receiver;
		this.orderStatus = OrderStatus.PENDING;
		this.createdAt = LocalDateTime.now();
		//this.totalPrice = totalPrice;
	}

	public static Order create(User user, Receiver receiver){

		return new Order(
			user,
			receiver
			//totalPrice
		);
	}

	public void mapToOrder(OrderProduct orderProduct){
		this.orderProducts.add(orderProduct);
	}

	/*
	public void calculateTotalPrice(){
		this.totalPrice = orderProducts.stream()
			.mapToLong(p -> p.getPrice() * p.getQuantity()) //각 요쇼를 int로 변환
			.sum();

	}*/

	//사용자가 주문 수정(배송 정보 수정)
	public void update(Receiver receiver){

		if(canUpdate()){
			this.receiver = receiver;
		}

	}
	//사용자 주문 삭제
	public void cancel(){

		if(canUpdate()){
			this.orderStatus = OrderStatus.CANCELLED;
		}
	}

	//수정 가능한 상태인지 확인
	private boolean canUpdate(){

		if(orderStatus == OrderStatus.PENDING || orderStatus == OrderStatus.COMPLETED){
			return true;
		}else{
			return false;
		}
	}

}