package com.product.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InsufficientProducts.class)
	public ResponseEntity<ErrorResponse> handleInsufficientProductsException(InsufficientProducts ex,HttpServletRequest httpServletRequest){
		
		ErrorResponse errorResponse=new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Bad Request",
				ex.getMessage(),
				httpServletRequest.getRequestURI());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
				
	}

}
