package com.kt.dto.orderproduct;

import com.kt.domain.orderproduct.OrderProduct;


public class OrderProductResponse {

	public record UserProductDetail(

		String productName,
		Long quantity,
		Long price

	){
		public static UserProductDetail from(OrderProduct orderProduct){

			return new UserProductDetail(
				orderProduct.getProduct().getName(),
				orderProduct.getQuantity(),
				orderProduct.getPrice()
			);
		}

	}

	public record ProductNameList(

		String ProductName
	){
		public static ProductNameList from(OrderProduct orderProduct){

			return new ProductNameList(
				orderProduct.getProduct().getName()
			);
		}
	}
}
