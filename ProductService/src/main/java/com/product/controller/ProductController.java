package com.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable("id") int id) {
		System.out.println(id);
		 productService.delete(id);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProduct(){
		return productService.getAllProduct();
	}
}

