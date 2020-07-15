package com.epam.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	private String customerName;
	
	private LocalDateTime orderTime;
	
	private String shippingAddress;
	
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	private Double total;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Order(Integer orderId, String customerName, LocalDateTime orderTime, String shippingAddress,
			List<OrderItem> items, Double total) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.orderTime = orderTime;
		this.shippingAddress = shippingAddress;
		this.items = items;
		this.total = total;
	}

	public Order() {
		super();
	}
	
	
	
	

}
