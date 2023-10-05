package com.sinensia.exceptions;

public class CursoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CursoNotFoundException(String message) {
		super(message);
	}

}
