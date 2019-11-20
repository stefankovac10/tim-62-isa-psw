package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.ClinicCenter;
import com.ProjectCC.dero.model.ClinicCenterAdministrator;
import com.ProjectCC.dero.service.ClinicCenterAdministratorService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/ccadmin")
public class ClinicCenterAdministratorController {

    private ClinicCenterAdministratorService clinicCenterAdministratorService;

    @Autowired
    public ClinicCenterAdministratorController(ClinicCenterAdministratorService clinicCenterAdministratorService) {
        this.clinicCenterAdministratorService = clinicCenterAdministratorService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO){
        ClinicCenter clinicCenter= new ClinicCenter();
        ClinicCenterAdministrator clinicCenterAdministrator = new ClinicCenterAdministrator(userDTO.getFirstName(),userDTO.getLastName(),userDTO.getJmbg(),
                userDTO.getPassword(), userDTO.getEmail(), userDTO.getAddress(), userDTO.getCity(), userDTO.getCountry(), userDTO.getTelephone(), clinicCenter);

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setFirstName(clinicCenterAdministrator.getFirstName());
        userDTO1.setLastName(clinicCenterAdministrator.getLastName());
        userDTO1.setAddress(clinicCenterAdministrator.getAddress());
        userDTO1.setCity(clinicCenterAdministrator.getCity());
        userDTO1.setCountry(clinicCenterAdministrator.getCountry());
        userDTO1.setEmail(clinicCenterAdministrator.getEmail());
        userDTO1.setId(clinicCenterAdministrator.getId());
        userDTO1.setJmbg(clinicCenterAdministrator.getJmbg());
        userDTO1.setPassword(clinicCenterAdministrator.getPassword());
        userDTO1.setTelephone(clinicCenterAdministrator.getTelephone());


        clinicCenterAdministrator = clinicCenterAdministratorService.save(clinicCenterAdministrator);
        return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getCourse(@PathVariable Long id) {

        ClinicCenterAdministrator clinicCenterAdministrator=clinicCenterAdministratorService.findOne(id);

        if (clinicCenterAdministrator == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(clinicCenterAdministrator.getFirstName());
        userDTO.setLastName(clinicCenterAdministrator.getLastName());
        userDTO.setAddress(clinicCenterAdministrator.getAddress());
        userDTO.setCity(clinicCenterAdministrator.getCity());
        userDTO.setCountry(clinicCenterAdministrator.getCountry());
        userDTO.setEmail(clinicCenterAdministrator.getEmail());
        userDTO.setId(clinicCenterAdministrator.getId());
        userDTO.setJmbg(clinicCenterAdministrator.getJmbg());
        userDTO.setPassword(clinicCenterAdministrator.getPassword());
        userDTO.setTelephone(clinicCenterAdministrator.getTelephone());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
