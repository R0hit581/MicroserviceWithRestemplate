package com.product.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.entity.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	ProductService(ProductRepository productRepository){
		this.productRepository=productRepository;
	}

	public ResponseEntity<?> add(Product product) {
		// TODO Auto-generated method stub
		Product p= productRepository.save(product);
		return ResponseEntity.ok().body(p);
	}

}
