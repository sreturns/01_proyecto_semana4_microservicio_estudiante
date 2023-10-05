package com.sinensia.exceptions;


public class InscripcionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InscripcionNotFoundException(String message){
        super(message);
    }
}
