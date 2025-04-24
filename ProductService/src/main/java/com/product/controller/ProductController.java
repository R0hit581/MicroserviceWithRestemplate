package com.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		// TODO Auto-generated constructor stub
		this.productService=productService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody Product product){
		return productService.add(product);
	}
}

