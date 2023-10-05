package com.sinensia.exceptions;

public class CursoOutOfPlaces extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CursoOutOfPlaces(String message) {
        super(message);
    }
}
