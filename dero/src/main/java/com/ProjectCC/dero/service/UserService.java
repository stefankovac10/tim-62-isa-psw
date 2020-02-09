package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.exceptions.ClinicNotFoundException;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ClinicAdministratorRepository clinicAdministratorRepository;
    private ClinicRepository clinicRepository;
    private ModelMapper modelMapper;
    private MedicalStaffRepository medicalStaffRepository;


    @Autowired
    public UserService(UserRepository userRepository, ClinicAdministratorRepository clinicAdministratorRepository, ClinicRepository clinicRepository, ModelMapper modelMapper, MedicalStaffRepository medicalStaffRepository) {
        this.userRepository = userRepository;
        this.clinicAdministratorRepository = clinicAdministratorRepository;
        this.clinicRepository = clinicRepository;
        this.medicalStaffRepository = medicalStaffRepository;
        this.modelMapper = modelMapper;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public ResponseEntity<UserDTO> findByEmail(String email) {
        Optional<User> opt = Optional.ofNullable(this.userRepository.findByEmail(email));
        return getUserDTOResponseEntity(opt);
    }

    public ResponseEntity<UserDTO> findById(Long id) {
        Optional<User> opt = this.userRepository.findById(id);
        return getUserDTOResponseEntity(opt);
    }

    private ResponseEntity<UserDTO> getUserDTOResponseEntity(Optional<User> opt) {
        UserDTO userDTO = null;
        if (opt.isPresent()) {
            User user = opt.get();
            userDTO = UserDTO.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .jmbg(user.getJmbg())
                    .telephone(user.getTelephone())
                    .country(user.getCountry())
                    .city(user.getCity())
                    .address(user.getAddress())
                    .build();
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> edit(UserDTO userDTO) {
        Optional<User> opt = this.userRepository.findById(userDTO.getId());
        User user = opt.get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setJmbg(userDTO.getJmbg());
        user.setTelephone(userDTO.getTelephone());
        this.userRepository.save(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteById(Long id) {
        this.userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<ClinicAdministratorDTO> getAdmin(String email) {
        ClinicAdministrator clinicAdministrator = this.clinicAdministratorRepository.findByEmail(email);
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(clinicAdministrator.getClinic().getId());

        if (!optionalClinic.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Clinic clinic = optionalClinic.get();
        ClinicDTO clinicDTO = ClinicDTO.builder()
                .name(clinic.getName())
                .address(clinic.getAddress())
                .description(clinic.getDescription())
                .grade(clinic.getGrade())
                .income(clinic.getIncome())
                .id(clinic.getId())
                .build();

        ClinicAdministratorDTO cadminDTO = ClinicAdministratorDTO.builder()
                .firstName(clinicAdministrator.getFirstName())
                .lastName(clinicAdministrator.getLastName())
                .jmbg(clinicAdministrator.getJmbg())
                .city(clinicAdministrator.getCity())
                .country(clinicAdministrator.getCountry())
                .address(clinicAdministrator.getAddress())
                .telephone(clinicAdministrator.getTelephone())
                .email(clinicAdministrator.getEmail())
                .clinic(clinicDTO)
                .id(clinicAdministrator.getId()).build();

        return new ResponseEntity<>(cadminDTO, HttpStatus.OK);
    }

    public Boolean findUserByEmail(String email) {
        Optional<User> opt = Optional.ofNullable(this.userRepository.findByEmail(email));
        User user = opt.get();

        return user.getLastPasswordResetDate() == null;
    }

    public ResponseEntity<List<VacationRequestDTO>> getVacations(String email) {
        MedicalStaff medicalStaff = (MedicalStaff) userRepository.findByEmail(email);
        List<VacationRequestDTO> vacationRequestDTOS = new ArrayList<>();
        for(VacationRequest vacationRequest: medicalStaff.getVacationRequest()){
            if(vacationRequest.isAccepted() == true) {
                vacationRequestDTOS.add(VacationRequestDTO.builder()
                        .endDate(vacationRequest.getEndDate())
                        .startDate(vacationRequest.getStartDate())
                        .id(vacationRequest.getId())
                        .accepted(vacationRequest.isAccepted())
                        .build());
            }
        }
        return new ResponseEntity<>(vacationRequestDTOS, HttpStatus.OK);
    }

    public ResponseEntity<MedicalStaffDTO> getMedicalStaff(String email) {
        MedicalStaff medicalStaff = this.medicalStaffRepository.findByEmail(email);
        Clinic clinic = this.clinicRepository.findById(medicalStaff.getClinic().getId()).orElseThrow(ClinicNotFoundException::new);

        ClinicDTO clinicDTO = ClinicDTO.builder()
                .name(clinic.getName())
                .address(clinic.getAddress())
                .description(clinic.getDescription())
                .grade(clinic.getGrade())
                .income(clinic.getIncome())
                .id(clinic.getId())
                .build();

        MedicalStaffDTO medicalStaffDTO = MedicalStaffDTO.builder()
                .firstName(medicalStaff.getFirstName())
                .lastName(medicalStaff.getLastName())
                .jmbg(medicalStaff.getJmbg())
                .city(medicalStaff.getCity())
                .country(medicalStaff.getCountry())
                .address(medicalStaff.getAddress())
                .telephone(medicalStaff.getTelephone())
                .email(medicalStaff.getEmail())
                .clinic(clinicDTO)
                .type(medicalStaff instanceof Doctor ? "doctor" : "nurse")
                .id(medicalStaff.getId()).build();

        return new ResponseEntity<>(medicalStaffDTO, HttpStatus.OK);
    }
}
