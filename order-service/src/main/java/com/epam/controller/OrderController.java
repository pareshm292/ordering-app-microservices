package com.epam.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.OrderNotFoundException;
import com.epam.model.Order;
import com.epam.model.OrderItem;
import com.epam.repo.OrderRepository;

@RestController
public class OrderController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	// CREATE ORDER
	// GET ORDER
	// DELETE ORDER
	
	
	@Autowired
	private OrderRepository repository;
	
	@PostMapping("/")
	public Order create(@RequestBody Order order) {
		LOGGER.info("Order create : {}" , order);
		return repository.save(order);
	}
	
	@GetMapping("/{id}")
	public Order findByOrderId(@PathVariable("id") Integer id) throws OrderNotFoundException {
		LOGGER.info("Order find: id={}", id);
		Optional<Order> itemOptional =  repository.findById(id);
		if(itemOptional.isPresent()) {
			return itemOptional.get();
		}
		else {
			throw new OrderNotFoundException("Order with specified id " + id + " not found.");
		}
	}
	
	@PutMapping("/{id}")
	public OrderItem updateQuantity(@PathVariable("id") Integer id , @RequestBody OrderItem item) throws OrderItemNotFoundException {
		
		LOGGER.info("OrderItem update : id={}", id);
		Optional<OrderItem> itemOptional =  repository.findById(id);
		if(itemOptional.isPresent()) {
			OrderItem tempItem =  itemOptional.get();
			tempItem.setQuantity(item.getQuantity());
			return repository.save(tempItem);
		}
		else {
			throw new OrderItemNotFoundException("Item with specified id " + id + " not found.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) throws OrderItemNotFoundException {
		LOGGER.info("OrderItem delete : id={}", id);

		repository.deleteById(id);
		
		return ResponseEntity.ok(id);
	}
	
}
