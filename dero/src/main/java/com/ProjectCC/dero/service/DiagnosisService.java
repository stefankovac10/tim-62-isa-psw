package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DiagnosisDTO;
import com.ProjectCC.dero.model.Diagnosis;
import com.ProjectCC.dero.repository.DiagnosisRepository;
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
public class DiagnosisService {

    private DiagnosisRepository diagnosisRepository;
    private ModelMapper modelMapper;

    @Autowired
    public DiagnosisService(DiagnosisRepository diagnosisRepository, ModelMapper modelMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.modelMapper = modelMapper;
    }

    public List<DiagnosisDTO> findAll() {
        List<Diagnosis> diagnoses = diagnosisRepository.findAll();

        List<DiagnosisDTO> diagnosisDTOS = new ArrayList<>();
        for (Diagnosis d : diagnoses) {
            diagnosisDTOS.add(modelMapper.map(d, DiagnosisDTO.class));
        }
        return diagnosisDTOS;
    }

    public List<DiagnosisDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC,"name"));
        Page<Diagnosis> diagnoses = diagnosisRepository.findAll(pageable);

        List<DiagnosisDTO> diagnosisDTOS = new ArrayList<>();
        for (Diagnosis d : diagnoses) {
            diagnosisDTOS.add(DiagnosisDTO.builder()
                    .code(d.getCode())
                    .description(d.getDescription())
                    .name(d.getName())
                    .id(d.getId())
                    .pages(diagnoses.getTotalPages()).build());
        }
        return diagnosisDTOS;
    }

    public Diagnosis save(Diagnosis diagnosis) {
        Diagnosis diagnosis_find = diagnosisRepository.findByName(diagnosis.getName());
        Diagnosis diagnosis_find1 = diagnosisRepository.findByCode(diagnosis.getCode());
        if (diagnosis_find == null && diagnosis_find1 == null) {
            return diagnosisRepository.save(diagnosis);
        }
        return null;
    }

    public ResponseEntity<Void> remove(Long id) {
        Diagnosis diagnosis  = diagnosisRepository.findById(id).orElseGet(null);

        if (diagnosis != null) {
            int i = diagnosis.getExaminations().size();
            if(i == 0) {
                diagnosisRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Diagnosis findOne(Long id) {
        return diagnosisRepository.findById(id).orElseGet(null);
    }

    public void update(Diagnosis diagnosis) {
        Diagnosis diagnosis_find = diagnosisRepository.findByName(diagnosis.getName());
        Diagnosis diagnosis_find2 = diagnosisRepository.findByCode(diagnosis.getCode());

        if (diagnosis_find == null || diagnosis_find2 == null || diagnosis_find.getId() == diagnosis.getId()) {
            diagnosisRepository.update(diagnosis.getName(), diagnosis.getCode(), diagnosis.getDescription(), diagnosis.getId());
        }
    }
}
