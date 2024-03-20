package com.javaweb.customerexception;

public class FieldRequiredException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FieldRequiredException(String massage) {
		super(massage);
	}
	
}
