package com.epam.advisor;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.epam.exception.OrderItemNotFoundException;

@ControllerAdvice
public class ControllerAdvisor {


	@ExceptionHandler(OrderItemNotFoundException.class)
	public ResponseEntity<?> handleInsufficientStockException(
			OrderItemNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Invalid Item requested!");

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
