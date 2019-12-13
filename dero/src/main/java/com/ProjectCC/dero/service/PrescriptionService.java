package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.dto.MedicationDTO;
import com.ProjectCC.dero.dto.NurseDTO;
import com.ProjectCC.dero.dto.PrescriptionDTO;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.Medication;
import com.ProjectCC.dero.model.Prescription;
import com.ProjectCC.dero.repository.PrescriptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PrescriptionService {

    private PrescriptionRepository prescriptionRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository,ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.modelMapper = modelMapper;
    }

    public List<PrescriptionDTO> findAll(){
        List<Prescription> prescriptions = prescriptionRepository.findAll();

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

    public void certify(Long  id) {
        Prescription prescription = prescriptionRepository.findById(id).orElseGet(null);

        if(prescription !=null){
            prescription.setCertified(true);
            prescriptionRepository.save(prescription);
        }
    }

}
