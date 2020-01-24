package com.ProjectCC.dero.controller;


import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
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
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }


    @GetMapping(value = "/mail/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email) {
        return this.userService.findByEmail(email);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<Boolean> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(this.userService.findUserByEmail(email), HttpStatus.OK);
    }


    @GetMapping(value = "/profile/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO user) {
        return this.userService.edit(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return this.userService.deleteById(id);
    }

}
