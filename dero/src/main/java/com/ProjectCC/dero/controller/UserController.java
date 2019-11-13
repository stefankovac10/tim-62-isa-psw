package com.ProjectCC.dero.controller;


import com.ProjectCC.dero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
