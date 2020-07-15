package com.epam.exception;

public class OrderItemNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderItemNotFoundException(String exception) {
		super(exception);
	}

}
