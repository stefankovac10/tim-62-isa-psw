package com.ProjectCC.dero.security.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationRequest {

    private String email;
    private String password;

    public JwtAuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public JwtAuthenticationRequest() {
        super();
    }
}
