package com.orders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orders.entity.Orders;
import com.orders.service.OrderService;

@RestController
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addOrder(@RequestBody Orders order){
		
		return new ResponseEntity<>(orderService.addOrder(order),HttpStatus.OK);
	}

}
