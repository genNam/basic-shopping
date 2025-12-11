package com.kt.service;

import org.springframework.stereotype.Service;

import com.kt.domain.product.Product;
import com.kt.repository.product.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public void create(String name, Long price, Long quantity) {
		productRepository.save(
			new Product(name, price, quantity)
		);
	}
	public void update(Long id, String name, Long price, Long quantity) {
		var product = productRepository.findByIdOrThrow(id); //리파지토리에 findBy~ 메서드를 호출하는데 id를 전달
		//id가 있으면 id가 저장되고, 없으면 예외처리

		product.update(name, price, quantity); //엔티티에 이름,가격, 수량 전달
	}
}