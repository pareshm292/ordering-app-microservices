package com.epam.advisor;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.epam.exception.InsufficientStockException;
import com.epam.exception.OrderNotFoundException;

@ControllerAdvice
public class ControllerAdvisor {


	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<?> handleInsufficientStockException(
			InsufficientStockException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Insufficient Stock!");

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Object> handleNodataFoundException(
			OrderNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "No Order found!");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
