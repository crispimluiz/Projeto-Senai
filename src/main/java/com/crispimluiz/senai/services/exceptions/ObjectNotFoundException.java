package com.crispimluiz.senai.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	// Extender a biblioteca que extende o tratamento de erro
	private static final long serialVersionUID = 1L;

	// Recebe a msg
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	//Recebe a msg e a causa.
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}