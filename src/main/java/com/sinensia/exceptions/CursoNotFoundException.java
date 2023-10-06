package com.sinensia.exceptions;

/**
 * Excepcion personalilzada
 * 
 * @see com.sinensia.service.InscripcionServiceImpl
 * @see com.sinensia.controller.InscripcionController
 */
public class CursoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CursoNotFoundException(String message) {
		super(message);
	}

}
