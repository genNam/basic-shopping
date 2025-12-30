package com.kt.dto.orderproduct;

import com.kt.domain.orderproduct.OrderProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderProductResponse {

	public record UserProductDetail(

		@NotBlank
		String productName,
		@NotNull
		Long quantity,
		@NotNull
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
		@NotBlank
		String ProductName
	){
		public static ProductNameList from(OrderProduct orderProduct){

			return new ProductNameList(
				orderProduct.getProduct().getName()
			);
		}
	}
}
