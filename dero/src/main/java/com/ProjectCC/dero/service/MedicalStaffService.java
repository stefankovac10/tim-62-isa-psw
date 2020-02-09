package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.MedicalStaffDTO;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.MedicalStaff;
import com.ProjectCC.dero.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicalStaffService {

    private MedicalStaffRepository medicalStaffRepository;

    @Autowired
    public MedicalStaffService(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }

    public ResponseEntity<MedicalStaffDTO> getByEmail(String email) {
        MedicalStaff medicalStaff = this.medicalStaffRepository.findByEmail(email);

        MedicalStaffDTO medicalStaffDTO = MedicalStaffDTO.builder()
                .lastName(medicalStaff.getLastName())
                .firstName(medicalStaff.getFirstName())
                .id(medicalStaff.getId())
                .type(medicalStaff instanceof Doctor ? "doctor" : "nurse").build();

        return new ResponseEntity<>(medicalStaffDTO, HttpStatus.OK);
    }
}
