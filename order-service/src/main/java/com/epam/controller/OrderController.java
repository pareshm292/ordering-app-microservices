package com.epam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exception.InsufficientStockException;
import com.epam.exception.OrderNotFoundException;
import com.epam.model.Order;
import com.epam.service.OrderService;

@RestController
public class OrderController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	// CREATE ORDER
	// GET ORDER
	// DELETE ORDER
	
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/")
	public Order create(@RequestBody Order order) throws InsufficientStockException {
		LOGGER.info("Order create : {}" , order);
		return orderService.save(order);
	}
	
	@GetMapping("/{id}")
	public Order findByOrderId(@PathVariable("id") Integer id) throws OrderNotFoundException {
		return orderService.getOrder(id);
	}
	
	/*
	 * @PutMapping("/{id}") public OrderItem updateQuantity(@PathVariable("id")
	 * Integer id , @RequestBody OrderItem item) throws OrderItemNotFoundException {
	 * 
	 * LOGGER.info("OrderItem update : id={}", id); Optional<OrderItem> itemOptional
	 * = repository.findById(id); if(itemOptional.isPresent()) { OrderItem tempItem
	 * = itemOptional.get(); tempItem.setQuantity(item.getQuantity()); return
	 * repository.save(tempItem); } else { throw new
	 * OrderItemNotFoundException("Item with specified id " + id + " not found."); }
	 * }
	 */
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) {
		LOGGER.info("Order delete : id={}", id);

		orderService.deleteById(id);
		
		return ResponseEntity.ok(id);
	}
	
}
