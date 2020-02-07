package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.dto.MedicationDTO;
import com.ProjectCC.dero.dto.PrescriptionDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.PrescriptionRepository;
import com.ProjectCC.dero.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PrescriptionService {

    private PrescriptionRepository prescriptionRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PrescriptionService(UserRepository userRepository, PrescriptionRepository prescriptionRepository,ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<PrescriptionDTO> findAll(String email){
        MedicalStaff user = (MedicalStaff)userRepository.findByEmail(email);
        Long id = user.getClinic().getId();

        List<Prescription> prescriptions = prescriptionRepository.findAll(id);

        List<PrescriptionDTO> prescriptionDTOS = new ArrayList<>();
        for (Prescription p : prescriptions) {
            DoctorDTO doctor = DoctorDTO.builder()
                                .firstName(p.getDoctor().getFirstName())
                                .lastName(p.getDoctor().getLastName())
                                .build();
            PrescriptionDTO pDTO = PrescriptionDTO.builder()
                                    .certified(p.getCertified())
                                    .id(p.getId())
                                    .doctor(doctor)
                                    //.nurse(modelMapper.map(p.getNurse(), NurseDTO.class))
                                    .build();

            List<MedicationDTO>  medications = new ArrayList<>();
            for(Medication med: p.getMedication()){
                medications.add(modelMapper.map(med, MedicationDTO.class));
            }
            pDTO.setMedication(medications);

            prescriptionDTOS.add(pDTO);
        }

        return prescriptionDTOS;
    }

    public void addPrescription(PrescriptionDTO prescriptionDTO){
        Set<Medication> medications =  new HashSet<>();

        for(MedicationDTO med: prescriptionDTO.getMedication()){
            medications.add(modelMapper.map(med, Medication.class));
        }

        Prescription prescription = Prescription.builder()
                                    .certified(false)
                                    .medication(medications)
                                    .build();

        prescriptionRepository.save(prescription);

    }

    public ResponseEntity<Void> certify(Long id, String email) {
        Prescription prescription = prescriptionRepository.findById(id).orElseGet(null);

        if(prescription !=null && prescription.getCertified()==false){
            Nurse user = (Nurse) userRepository.findByEmail(email);
            prescription.setNurse(user);
            prescription.setCertified(true);
            prescriptionRepository.save(prescription);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
