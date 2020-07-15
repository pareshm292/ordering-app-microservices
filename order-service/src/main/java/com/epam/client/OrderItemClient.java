package com.epam.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.model.OrderItem;

@FeignClient(name = "item-service")
public interface OrderItemClient {

	@GetMapping("/{id}")
	public OrderItem findById(@PathVariable Integer id);
	
	@GetMapping("/{name}")
	public OrderItem findByName(@PathVariable String name);
	
	@PutMapping("/{id}")
	public OrderItem updateQuantity(@PathVariable("id") Integer id , @RequestBody OrderItem item);
	
	@PostMapping("/")
	public OrderItem create(@RequestBody OrderItem item);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id);
	
	
	
	
}
