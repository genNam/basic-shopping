package com.kt.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kt.domain.product.Product;
import com.kt.dto.product.ProductRequest;
import com.kt.dto.product.ProductResponse;
import com.kt.repository.product.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public void create(ProductRequest.Create request) {
		productRepository.save(
			new Product(
				request.getName(),
				request.getPrice(),
				request.getQuantity()
			)
		);
	}
	public void update(Long id, ProductRequest.Update request) {
		var product = productRepository.findByIdOrThrow(id); //리파지토리에 findBy~ 메서드를 호출하는데 id를 전달
		//id가 있으면 id가 저장되고, 없으면 예외처리

		product.update(
			request.getName(),
			request.getPrice(),
			request.getQuantity()
		); //엔티티에 이름,가격, 수량 전달
	}

	public void delete(Long id) {

		var product = productRepository.findByIdOrThrow(id);

		product.delete();
	}

	public void inActivate(Long id) {
		var product = productRepository.findByIdOrThrow(id);

		product.inActivate();
	}

	public void activate(Long id) {
		var product = productRepository.findByIdOrThrow(id);

		product.activate();
	}

	/*
	public void soldOut(Long id){

		var product = productRepository.findByIdOrThrow(id);

		product.soldOut();
	}*/

	//관리자 리스트 조회
	public List<ProductResponse.AdminList> adminList(){

		List<Product> products = productRepository.findAll();
		List<ProductResponse.AdminList> response = products.stream()
			.map(product -> ProductResponse.AdminList.from(product))
			.toList();

		return response;

	}

	//관리자 상세 조회
	public ProductResponse.AdminDetail adminDetail(Long id){

		Product product = productRepository.findByIdOrThrow(id);
		ProductResponse.AdminDetail response = ProductResponse.AdminDetail.from(product);

		return response;
	}

	//사용자 리스트 조회
	public List<ProductResponse.UserList> getUserList(){

		var products = productRepository.findAll();
		List<ProductResponse.UserList> response = products.stream()
			.map(product -> ProductResponse.UserList.from(product))
			.toList();

		return response;
	}

	//사용자 상세 조회
	public ProductResponse.UserDetail getUserDetail(Long id){

		var product = productRepository.findByIdOrThrow(id);

		var status = product.canProvide();
		ProductResponse.UserDetail response = ProductResponse.UserDetail.of(product,status);

		return response;
	}


}