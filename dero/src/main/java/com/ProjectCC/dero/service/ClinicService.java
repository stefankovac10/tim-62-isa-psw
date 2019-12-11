package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.dto.MedicalStaffDTO;
import com.ProjectCC.dero.dto.RoomDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.MedicalStaff;
import com.ProjectCC.dero.model.Room;
import com.ProjectCC.dero.repository.ClinicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    private ModelMapper modelMapper;

    private ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository, ModelMapper modelMapper) {
        this.clinicRepository = clinicRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<ClinicDTO>> findAll() {
        List<Clinic> clinics = clinicRepository.findAll();
        List<ClinicDTO> clinicDTOS = new ArrayList<>();

        for (Clinic c : clinics) {
            clinicDTOS.add(modelMapper.map(c, ClinicDTO.class));
        }

        return new ResponseEntity<>(clinicDTOS, HttpStatus.OK);
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

    public ClinicDTO update(ClinicDTO clinicDTO) {
        Optional<Clinic> clinic_find = clinicRepository.findById(clinicDTO.getId());
        Clinic clinic = clinic_find.get();
        clinic.setName(clinicDTO.getName());
        clinic.setAddress(clinicDTO.getAddress());
        clinic.setDescription(clinicDTO.getDescription());

        if (clinic != null) {
            clinic = clinicRepository.save(clinic);
            return modelMapper.map(clinic, ClinicDTO.class);
        } else return null;
    }

    public Clinic findByName(String name) {
        return clinicRepository.findByName(name);
    }

    public ResponseEntity<ClinicDTO> findById(Long id) {

        Optional<Clinic> opt = clinicRepository.findById(id);
        Clinic clinic = opt.get();

//        return cinemaStore.findAllCinemas(pageable).map(cinema -> modelMapper.map(cinema, CinemaDto.class));

        List<MedicalStaffDTO> staff = new ArrayList<>();
        for (MedicalStaff s : clinic.getMedicalStaff()) {
            staff.add(modelMapper.map(s, MedicalStaffDTO.class));
//            staff.add(MedicalStaffDTO.builder()
//                    .firstName(s.getFirstName())
//                    .lastName(s.getLastName())
//                    .address(s.getAddress())
//                    .city(s.getCity())
//                    .country(s.getCountry())
//                    .email(s.getEmail())
//                    .id(s.getId())
//                    .jmbg(s.getJmbg())
//                    .telephone(s.getTelephone())
//                    .build());
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

    public ResponseEntity<List<ClinicDTO>> pronadjiPoImenuAdresiOpisu(ClinicDTO clinicDTO){
        String name = clinicDTO.getName();
        String address = clinicDTO.getAddress();
        String description = clinicDTO.getDescription();

        List<Clinic> clinics = clinicRepository.pronadjiKlinikePoImenuAdresiOpisu(name, address, description);
        List<ClinicDTO> clinicsDTO = new ArrayList<>();

        for (Clinic c : clinics) {
            clinicsDTO.add(modelMapper.map(c, ClinicDTO.class));
        }

        return new ResponseEntity<>(clinicsDTO, HttpStatus.OK);
    }
}
