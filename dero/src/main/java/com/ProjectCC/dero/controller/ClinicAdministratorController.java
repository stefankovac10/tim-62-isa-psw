package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ClinicAdministratorDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.ClinicAdministrator;
import com.ProjectCC.dero.service.ClinicAdministratorService;
import com.ProjectCC.dero.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/cadmin")
public class ClinicAdministratorController {
    private ClinicAdministratorService clinicAdministratorService;
    private ClinicService clinicService;

    @Autowired
    public ClinicAdministratorController(ClinicAdministratorService clinicAdministratorService, ClinicService clinicService) {
        this.clinicAdministratorService = clinicAdministratorService;
        this.clinicService = clinicService;
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Long> updateAdmin(@PathVariable ClinicAdministratorDTO cadminDTO) {
        ClinicAdministrator cadmin = new ClinicAdministrator();
        cadmin.setFirstName(cadminDTO.getFirstName());
        cadmin.setLastName(cadminDTO.getLastName());
        cadmin.setJmbg(cadminDTO.getJmbg());
        cadmin.setAddress(cadminDTO.getAddress());
        cadmin.setCity(cadminDTO.getCity());
        cadmin.setCountry(cadminDTO.getCountry());
        cadmin.setTelephone(cadminDTO.getTelephone());
        cadmin.setEmail(cadminDTO.getEmail());
        cadmin.setPassword(cadminDTO.getPassword());

        cadmin = clinicAdministratorService.save(cadmin);

        return new ResponseEntity<>(cadmin.getId(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ClinicAdministratorDTO> save(@RequestBody ClinicAdministratorDTO clinicAdministratorDTO) {
        ClinicAdministrator cadmin = new ClinicAdministrator(clinicAdministratorDTO);

        Clinic clinic = clinicService.findByName(clinicAdministratorDTO.getClinic());
        cadmin.setClinic(clinic);

        cadmin = clinicAdministratorService.save(cadmin);

        clinicAdministratorDTO.setId(cadmin.getId());
        return new ResponseEntity<>(clinicAdministratorDTO, HttpStatus.OK);
    }
}
