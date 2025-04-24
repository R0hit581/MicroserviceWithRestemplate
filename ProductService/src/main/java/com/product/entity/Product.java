package com.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private int productId;
	private String productName;
	private int productQty;
	private double productPrice;
	private double productEarnings;
	private int productAvailable;
	private int productSold;
}
