package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private User user;
    private boolean verified;

    public RegistrationRequest() {
    }
}
