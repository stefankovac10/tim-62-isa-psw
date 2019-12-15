package com.ProjectCC.dero.controller;


import com.ProjectCC.dero.dto.UserDTO;
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


    @GetMapping(value = "/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email) {
        User user = userService.findByEmail(email);
        UserDTO userDTO = UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .email(user.getEmail())
                .build();
        return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
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
