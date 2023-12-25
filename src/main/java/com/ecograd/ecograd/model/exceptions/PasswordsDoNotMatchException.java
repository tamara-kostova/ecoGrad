package com.ecograd.ecograd.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super("The Password and Repeat password fields do not match.");
    }
}
