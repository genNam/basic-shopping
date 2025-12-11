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
}