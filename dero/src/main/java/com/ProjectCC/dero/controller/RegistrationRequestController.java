package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.RegistrationRequestDTO;
import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.service.RegistrationRequestService;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    public RegistrationRequestController(RegistrationRequestService registrationRequestService, ModelMapper modelMapper) {
        this.registrationRequestService = registrationRequestService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO){
        RegistrationRequest registrationRequest = RegistrationRequest.builder()
                                                    .firstName(userDTO.getFirstName())
                                                    .lastName(userDTO.getLastName())
                                                    .address(userDTO.getAddress())
                                                    .city(userDTO.getCity())
                                                    .country(userDTO.getCountry())
                                                    .password(userDTO.getPassword())
                                                    .email(userDTO.getEmail())
                                                    .telephone(userDTO.getTelephone())
                                                    .jmbg(userDTO.getJmbg())
                                                    .verified(false)
                                                    .build();

        registrationRequest = registrationRequestService.save(registrationRequest);

        userDTO.setId(registrationRequest.getId());

        if(registrationRequest != null)
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<RegistrationRequestDTO>> findAll(){
        List<RegistrationRequest> registrationRequests = registrationRequestService.findAll();

        List<RegistrationRequestDTO> registrationRequestsDTOS = new ArrayList<>();
        for(RegistrationRequest r : registrationRequests){
            registrationRequestsDTOS.add(modelMapper.map(r,RegistrationRequestDTO.class));
        }

        return new ResponseEntity<>(registrationRequestsDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getRegistrationRequest(@PathVariable Long id) {

        RegistrationRequest registrationRequest = registrationRequestService.findById(id);

        if (registrationRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserDTO.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .address(registrationRequest.getAddress())
                .city(registrationRequest.getCity())
                .country(registrationRequest.getCountry())
                .email(registrationRequest.getEmail())
                .jmbg(registrationRequest.getJmbg())
                .telephone(registrationRequest.getTelephone())
                .id(registrationRequest.getId())
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {

        RegistrationRequest registrationRequest  = registrationRequestService.findOne(id);

        if (registrationRequest != null) {
            registrationRequestService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/accept/{id}")
    public ResponseEntity<RegistrationRequestDTO> update(@PathVariable Long id){
        RegistrationRequest registrationRequest = registrationRequestService.findById(id);
        registrationRequest.setVerified(true);
        registrationRequestService.update(registrationRequest);
        return new ResponseEntity<>(modelMapper.map(registrationRequest, RegistrationRequestDTO.class), HttpStatus.OK);

    }

}
