package com.epam.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.client.OrderItemClient;
import com.epam.exception.InsufficientStockException;
import com.epam.exception.OrderNotFoundException;
import com.epam.model.Order;
import com.epam.model.OrderItem;
import com.epam.repo.OrderRepository;

@Service
public class OrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderItemClient orderItemClient;
	
	@Autowired
	private OrderRepository repo;
	
	public Order save(Order order) throws InsufficientStockException {
		// save order
		Order newOrder = repo.save(order);
		
		// update quantities in inventory
		for (OrderItem orderItem : order.getItems()) {
			OrderItem inventoryItem = orderItemClient.findById(orderItem.getItemCode());
			if(inventoryItem.getQuantity() < orderItem.getQuantity()) {
				throw new InsufficientStockException("Not enough stock for item " + inventoryItem.getItemName());
			}
			orderItemClient.updateQuantity(inventoryItem.getItemCode(), 
					new OrderItem(inventoryItem.getItemCode(), inventoryItem.getItemName(), inventoryItem.getQuantity() - orderItem.getQuantity()));
		}
		return newOrder;
	}
	
	public Order getOrder(Integer orderId) throws OrderNotFoundException {
		
		LOGGER.info("Order find: id={}", orderId);
		Optional<Order> itemOptional =  repo.findById(orderId);
		if(itemOptional.isPresent()) {
			return itemOptional.get();
		}
		else {
			throw new OrderNotFoundException("Order with specified id " + orderId + " not found.");
		}
		
	}

	public void deleteById(Integer id) {

		repo.deleteById(id);
		
	}

}
