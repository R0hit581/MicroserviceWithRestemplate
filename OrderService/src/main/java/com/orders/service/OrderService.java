package com.orders.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orders.dto.ErrorResponse;
import com.orders.entity.Orders;
import com.orders.repository.OrdersRepository;

@Service
public class OrderService {

	private final OrdersRepository ordersRepository;
	
	private RestTemplate restTemplate;
	
	public OrderService(OrdersRepository ordersRepository) {
		this.ordersRepository=ordersRepository;
		this.restTemplate= new RestTemplate();
	}
	
	public ResponseEntity<?> addOrder(Orders order) {
	    String productServiceUrl = "http://localhost:8081/api/update";

	    try {
	        HttpEntity<Orders> requestEntity = new HttpEntity<>(order);

	        ResponseEntity<?> response = restTemplate.exchange(
	                productServiceUrl,
	                HttpMethod.PUT,
	                requestEntity,
	                Object.class
	        );

	        // Success - Save Order
	        ordersRepository.save(order);
	        return ResponseEntity.ok("Order placed successfully and Product updated!");
	    }
	    catch (HttpClientErrorException | HttpServerErrorException ex) {
	        // Read JSON body
	        String errorBody = ex.getResponseBodyAsString();

	        try {
	            // Parse JSON into ErrorResponse object
	            ObjectMapper objectMapper = new ObjectMapper();
	            ErrorResponse errorResponse = objectMapper.readValue(errorBody, ErrorResponse.class);

	            return ResponseEntity
	                    .status(ex.getStatusCode())
	                    .body(errorResponse);

	        } catch (Exception parseEx) {
	            // If parsing fails, just return raw body
	            return ResponseEntity
	                    .status(ex.getStatusCode())
	                    .body(errorBody);
	        }
	    }
	    catch (ResourceAccessException ex) {
	        return ResponseEntity
	                .status(HttpStatus.SERVICE_UNAVAILABLE)
	                .body("Product Service is currently unavailable. Please try again later.");
	    }
	    catch (Exception ex) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Something went wrong while placing the order.");
	    }
	}
	
}
