package com.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.dto.Orders;
import com.product.entity.Product;
import com.product.exceptions.InsufficientProducts;
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

	public ResponseEntity<?> updateProduct(Orders order) throws InsufficientProducts {
		// TODO Auto-generated method stub
		Product newProduct= productRepository.findById(order.getProductId()).orElse(null);
		if(order.getProductQty()>newProduct.getProductAvailable()) {
			throw new InsufficientProducts("not enough products available, available products are: "+newProduct.getProductAvailable());
		}
		newProduct.setProductAvailable(newProduct.getProductAvailable()-order.getProductQty());
		newProduct.setProductEarnings(newProduct.getProductEarnings()+newProduct.getProductPrice()*order.getProductQty());
		newProduct.setProductSold(newProduct.getProductSold()+ order.getProductQty());
		Product updatedProduct =productRepository.save(newProduct);	
		System.err.println("updated");
		return ResponseEntity.ok(updatedProduct);
	}

}
