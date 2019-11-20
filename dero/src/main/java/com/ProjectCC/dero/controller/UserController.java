package com.ProjectCC.dero.controller;


import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private UserService userService;




    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = new User();
        newUser = user;

        newUser = userService.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @GetMapping(value = "{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        User user = new User();

        user = userService.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }




}
