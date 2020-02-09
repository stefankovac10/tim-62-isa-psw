package com.ProjectCC.dero.exception;

public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public UserNotFoundException() {

    }

}
