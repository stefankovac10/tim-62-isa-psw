package com.ProjectCC.dero.service;


import com.ProjectCC.dero.dto.MedicationDTO;
import com.ProjectCC.dero.model.Medication;
import com.ProjectCC.dero.repository.MedicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationService {

    private MedicationRepository medicationRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository, ModelMapper modelMapper) {
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }

    public List<MedicationDTO> findAll() {
        List<Medication> medications = medicationRepository.findAll();

        List<MedicationDTO> medicationDTOS = new ArrayList<>();
        for (Medication m : medications) {
            medicationDTOS.add(modelMapper.map(m, MedicationDTO.class));
        }
        return medicationDTOS;
    }

    public List<MedicationDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC,"name"));
        Page<Medication> medications = medicationRepository.findAll(pageable);

        List<MedicationDTO> medicationDTOS = new ArrayList<>();
        for (Medication m : medications) {
            medicationDTOS.add(MedicationDTO.builder()
                                .code(m.getCode())
                                .description(m.getDescription())
                                .name(m.getName())
                                .id(m.getId())
                                .pages(medications.getTotalPages()).build());
        }
        return medicationDTOS;
    }

    public Medication save(Medication medication){
        Medication medication_find = medicationRepository.findByName(medication.getName());
        Medication medication_find1 = medicationRepository.findByCode(medication.getCode());
        if (medication_find == null && medication_find1 == null) {
            return medicationRepository.save(medication);
        }
        return null;

    }

    public ResponseEntity<Void> remove(Long id){
        Medication medication  = medicationRepository.findById(id).orElseGet(null);

        if (medication != null) {
            int i = medication.getPrescriptions().size();
            if(i == 0) {
                medicationRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Medication findOne(Long id) {
        return medicationRepository.findById(id).orElseGet(null);
    }

    public void update(Medication medication) {
        Medication medication_find = medicationRepository.findByName(medication.getName());
        Medication medication_find2 = medicationRepository.findByCode(medication.getCode());

        if (medication_find== null || medication_find2 == null || medication_find.getId() == medication.getId()) {
            medicationRepository.update(medication.getName(), medication.getCode(), medication.getDescription(), medication.getId());
        }
    }
}
