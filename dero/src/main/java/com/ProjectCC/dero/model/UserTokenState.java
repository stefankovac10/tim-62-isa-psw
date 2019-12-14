package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenState {
    private String accessToken;
    private Integer expiresIn;
    private String email;
    private String authority;

    public UserTokenState(String accessToken, Integer expiresIn, String email, String authority) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.email = email;
        this.authority = authority;
    }

    public UserTokenState(String accessToken, Integer expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.email = null;
        this.authority = null;
    }
}
