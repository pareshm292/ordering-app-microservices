package com.epam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemCode;
	
	private String itemName;
	
	private Double quantity;
	
	@ManyToOne
    @JoinColumn(name="orderId", nullable=false)
	private Order order;

	public Integer getItemCode() {
		return itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public OrderItem() {
		super();
	}

	public OrderItem(Integer itemCode, String itemName, Double quantity) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	
	
	
}
