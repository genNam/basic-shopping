package com.kt.domain.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;

import com.kt.common.exception.CustomException;
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

	//외부 노출 방지용 코드 만들기

	public void mapToOrderProduct(OrderProduct orderProduct) {
		this.orderproducts.add(orderProduct); //주문한 상품들에 대한 객체를 가져와서 리스트에 저장
	}

	public void update(String name, Long price, Long stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	public void delete() {
		this.status = ProductStatus.DELETED;
	}

	public void inActivate() {

		this.status = ProductStatus.IN_ACTIVATED;
	}

	public void activate() {
		this.status = ProductStatus.ACTIVATED;
	}

	public boolean canProvide(){

		return status == ProductStatus.ACTIVATED && stock > 0;
	}

	public void soldOut(){
		this.status = ProductStatus.SOLD_OUT;
	}

	//품절 : 상품(활성화) -> 토글 -> 비활성화 -> 토글 -> 활성화
	//상품품절(토글)
	public void toggleSoldOut(){

		canToggleSoldOut();

		if (status == ProductStatus.SOLD_OUT){
			activate();
		}else if(status == ProductStatus.ACTIVATED){
			soldOut();
		}
	}

	private void canToggleSoldOut(){

		if(status != ProductStatus.SOLD_OUT && status != ProductStatus.ACTIVATED){
			throw new CustomException(ErrorCode.PRODUCT_TOGGLE_NOT_ALLOWED);
		}

	}

}
