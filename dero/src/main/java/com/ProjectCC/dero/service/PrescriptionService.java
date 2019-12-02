package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.PrescriptionDTO;
import com.ProjectCC.dero.model.Prescription;
import com.ProjectCC.dero.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionService {

    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<PrescriptionDTO> findAll(){
        List<Prescription> prescriptions = prescriptionRepository.findAll();

        List<PrescriptionDTO> prescriptionDTOS = new ArrayList<>();
        for (Prescription p : prescriptions) {
            prescriptionDTOS.add(new PrescriptionDTO(p));
        }

        return prescriptionDTOS;
    }

    public void addPrescription(PrescriptionDTO prescriptionDTO){
        Prescription prescription = new Prescription(prescriptionDTO);

        prescriptionRepository.save(prescription);

    }

    public PrescriptionDTO certify(Long  id) {
        Prescription prescription = prescriptionRepository.findById(id).orElseGet(null);

        if(prescription !=null){
            prescription.setCertified(true);
            prescriptionRepository.save(prescription);
            return new PrescriptionDTO(prescription);
        }else{
            return null;
        }
    }

}
