package com.ProjectCC.dero.exceptions;

public class NoAvailableDoctorsForExaminationException extends RuntimeException {

    public NoAvailableDoctorsForExaminationException(String errorMessage) {
        super(errorMessage);
    }
}
