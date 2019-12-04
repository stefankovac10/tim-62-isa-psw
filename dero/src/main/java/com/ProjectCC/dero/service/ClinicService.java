package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.dto.MedicalStaffDTO;
import com.ProjectCC.dero.dto.RoomDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.MedicalStaff;
import com.ProjectCC.dero.model.Room;
import com.ProjectCC.dero.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {


    private ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public Clinic save(Clinic clinic){
        
        Clinic clinic_find = clinicRepository.findByName(clinic.getName());
        if(clinic_find == null){
            return clinicRepository.save(clinic);
        }

        return null;
    }

    public void remove(Long id){
        clinicRepository.deleteById(id);
    }

    public Clinic findOne(Long id) {
        return clinicRepository.findById(id).orElseGet(null);
    }

    public void update(Clinic clinic) {
        Clinic clinic_find = clinicRepository.findByName(clinic.getName());

        if (clinic_find == null || clinic_find.getId() == clinic.getId()) {
            clinicRepository.update(clinic.getName(), clinic.getAddress(), clinic.getDescription(), clinic.getId());
        }
    }

    public Clinic findByName(String name) {
        return clinicRepository.findByName(name);
    }

    public ResponseEntity<ClinicDTO> findById(Long id) {

        Optional<Clinic> opt = clinicRepository.findById(id);
        Clinic clinic = opt.get();

        List<MedicalStaffDTO> staff = new ArrayList<>();
        for (MedicalStaff s : clinic.getMedicalStaff()) {
            staff.add(MedicalStaffDTO.builder()
                    .firstName(s.getFirstName())
                    .lastName(s.getLastName())
                    .address(s.getAddress())
                    .city(s.getCity())
                    .country(s.getCountry())
                    .email(s.getEmail())
                    .id(s.getId())
                    .jmbg(s.getJmbg())
                    .telephone(s.getTelephone())
                    .build());
        }
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : clinic.getRooms()) {
            roomDTOS.add(RoomDTO.builder()
            .name(r.getName())
            .number(r.getNumber())
            .id(r.getId())
            .build());
        }

        ClinicDTO dto = ClinicDTO.builder()
                .name(clinic.getName())
                .description(clinic.getDescription())
                .address(clinic.getAddress())
                .id(clinic.getId())
                .medicalStaff(staff)
                .rooms(roomDTOS)
                .build();

        if (clinic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
