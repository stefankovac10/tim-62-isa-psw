package com.ProjectCC.dero.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
