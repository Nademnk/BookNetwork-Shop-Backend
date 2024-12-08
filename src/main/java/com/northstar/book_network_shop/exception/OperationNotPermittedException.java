package com.northstar.book_network_shop.exception;

public class OperationNotPermittedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555696072889927132L;

	public OperationNotPermittedException(String msg) {
		super(msg);
	}
	
}
