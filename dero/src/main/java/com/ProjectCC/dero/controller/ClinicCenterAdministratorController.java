package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ClinicCenterAdministratorDTO;
import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.ClinicCenterAdministrator;
import com.ProjectCC.dero.service.ClinicCenterAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/ccadmin")
public class ClinicCenterAdministratorController {

    private ClinicCenterAdministratorService clinicCenterAdministratorService;

    @Autowired
    public ClinicCenterAdministratorController(ClinicCenterAdministratorService clinicCenterAdministratorService) {
        this.clinicCenterAdministratorService = clinicCenterAdministratorService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ClinicCenterAdministratorDTO> save(@RequestBody ClinicCenterAdministratorDTO ccadminDTO){
        return new ResponseEntity<>(clinicCenterAdministratorService.save(ccadminDTO), HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<ClinicCenterAdministratorDTO> getCCAdmin(@PathVariable Long id) {

        ClinicCenterAdministrator clinicCenterAdministrator=clinicCenterAdministratorService.findOne(id);

        if (clinicCenterAdministrator == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ClinicCenterAdministratorDTO clinicCenterAdministratorDTO = ClinicCenterAdministratorDTO.builder()
                .firstName(clinicCenterAdministrator.getFirstName())
                .lastName(clinicCenterAdministrator.getLastName())
                .address(clinicCenterAdministrator.getAddress())
                .city(clinicCenterAdministrator.getCity())
                .country(clinicCenterAdministrator.getCountry())
                .email(clinicCenterAdministrator.getEmail())
                .jmbg(clinicCenterAdministrator.getJmbg())
                .telephone(clinicCenterAdministrator.getTelephone())
                .id(clinicCenterAdministrator.getId())
                .build();

        return new ResponseEntity<>(clinicCenterAdministratorDTO, HttpStatus.OK);
    }

}
