package com.product.service;

import java.util.List;

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
		product.setProductAvailable(product.getProductQty()-product.getProductSold());
		Product p= productRepository.save(product);
		return ResponseEntity.ok().body(p);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

	public ResponseEntity<List<Product>> getAllProduct() {
		// TODO Auto-generated method stub
		List<Product> prodList= productRepository.findAll();
		return ResponseEntity.ok(prodList);
	}

}
