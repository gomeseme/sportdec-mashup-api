package com.sportdec.search.mashup.api.domain.exception;

public class ServiceApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceApiException() {
		super();
	}

	public ServiceApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceApiException(String message) {
		super(message);
	}

	public ServiceApiException(Throwable cause) {
		super(cause);
	}	
}