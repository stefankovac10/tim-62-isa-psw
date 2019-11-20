package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Registration;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "api/regrequest")
public class RegistrationRequestController {
    private RegistrationRequestService registrationRequestService;

    @Autowired
    public RegistrationRequestController(RegistrationRequestService registrationRequestService) {
        this.registrationRequestService = registrationRequestService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO){
        RegistrationRequest registrationRequest = new RegistrationRequest(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getJmbg(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getAddress(),
                userDTO.getCity(),
                userDTO.getCountry(),
                userDTO.getTelephone(),
                false);

        registrationRequest = registrationRequestService.save(registrationRequest);

        userDTO.setId(registrationRequest.getId());

        if(registrationRequest != null)
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> findAll(){
        List<RegistrationRequest> registrationRequests = registrationRequestService.findAll();

        List<UserDTO> registrationRequestsDTOS = new ArrayList<>();
        for(RegistrationRequest r : registrationRequests){
            registrationRequestsDTOS.add(new UserDTO(r));
        }

        return new ResponseEntity<>(registrationRequestsDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getCourse(@PathVariable Long id) {

        RegistrationRequest registrationRequest = registrationRequestService.findById(id);

        if (registrationRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(registrationRequest), HttpStatus.OK);
    }
}
