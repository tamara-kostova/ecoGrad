package com.ecograd.ecograd.model.exception;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException(String username) {
        super(String.format("User with username %s doesn't exist",username));
    }
}
