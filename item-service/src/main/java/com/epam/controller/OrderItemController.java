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

import com.epam.exception.OrderItemNotFoundException;
import com.epam.model.OrderItem;
import com.epam.repo.OrderItemRepository;

@RestController
public class OrderItemController {

private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemController.class);
	
	@Autowired
	private OrderItemRepository repository;
	
	@PostMapping("/")
	public OrderItem create(@RequestBody OrderItem item) {
		LOGGER.info("OrderItem add: {}",item);
		return repository.save(item);
	}
	
	@GetMapping("/{id}")
	public OrderItem findByItemCode(@PathVariable("id") Integer id) throws OrderItemNotFoundException {
		LOGGER.info("OrderItem find: id={}", id);
		Optional<OrderItem> itemOptional =  repository.findById(id);
		if(itemOptional.isPresent()) {
			return itemOptional.get();
		}
		else {
			throw new OrderItemNotFoundException("Item with specified id " + id + " not found.");
		}
	}
	
	@GetMapping("/{name}")
	public OrderItem findByItemCode(@PathVariable("name") String name) throws OrderItemNotFoundException {
		LOGGER.info("OrderItem find: name={}", name);
		Optional<OrderItem> itemOptional =  repository.findByItemName(name);
		if(itemOptional.isPresent()) {
			return itemOptional.get();
		}
		else {
			throw new OrderItemNotFoundException("Item with specified id " + name + " not found.");
		}
	}
	
	@GetMapping("/")
	public List<OrderItem> findAll() {
		LOGGER.info("OrderItem find");
		return repository.findAll();
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
