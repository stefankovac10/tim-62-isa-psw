package com.ProjectCC.dero.exception;

public class BadExaminationRequest extends RuntimeException {
    public BadExaminationRequest(String errorMessage) {
        super(errorMessage);
    }
}
