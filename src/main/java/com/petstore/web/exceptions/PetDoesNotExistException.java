package com.petstore.web.exceptions;

public class PetDoesNotExistException extends Exception {
    public PetDoesNotExistException() {
        super();
    }


    public PetDoesNotExistException(String message) {
        super(message);
    }
}
