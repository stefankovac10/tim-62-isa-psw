package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.TypeOfExaminationDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.TypeOfExamination;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.ExaminationRepository;
import com.ProjectCC.dero.repository.TypeOfExaminationRepository;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeOfExaminationService {

    private TypeOfExaminationRepository typeOfExaminationRepository;
    private ExaminationRepository examinationRepository;
    private ClinicRepository clinicRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TypeOfExaminationService(TypeOfExaminationRepository typeOfExaminationRepository, ClinicRepository clinicRepository,
                                    ExaminationRepository examinationRepository, ModelMapper modelMapper) {
        this.typeOfExaminationRepository = typeOfExaminationRepository;
        this.examinationRepository = examinationRepository;
        this.clinicRepository = clinicRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<TypeOfExaminationDTO> update(TypeOfExaminationDTO typeDTO) {
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(typeDTO.getClinic().getId());
        if (!optionalClinic.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Clinic clinic = optionalClinic.get();
        TypeOfExamination type = modelMapper.map(typeDTO, TypeOfExamination.class);
        type.setClinic(clinic);
        this.typeOfExaminationRepository.save(type);
        return new ResponseEntity<>(typeDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<TypeOfExaminationDTO>> getAll() {
        List<TypeOfExamination> list = typeOfExaminationRepository.findAll();

        List<TypeOfExaminationDTO> listDTO = new ArrayList<>();
        for (TypeOfExamination t : list) {
            listDTO.add(TypeOfExaminationDTO.builder()
            .id(t.getId())
            .name(t.getName())
            .description(t.getDescription())
            .build());
        }

        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    public ResponseEntity<TypeOfExaminationDTO> add(TypeOfExaminationDTO typeDTO) {
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(typeDTO.getClinic().getId());
        if (!optionalClinic.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Clinic clinic = optionalClinic.get();
        TypeOfExamination type = modelMapper.map(typeDTO, TypeOfExamination.class);
        type.setClinic(clinic);
        type = this.typeOfExaminationRepository.save(type);
        typeDTO.setId(type.getId());
        return new ResponseEntity<>(typeDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<TypeOfExaminationDTO> delete(Long id) {
        DateTime now = new DateTime();
        Optional<TypeOfExamination> optionalTypeOfExamination = this.typeOfExaminationRepository.findById(id);
        if (!optionalTypeOfExamination.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        TypeOfExamination typeOfExamination = optionalTypeOfExamination.get();
        List<Examination> examinations = this.examinationRepository.findByType(typeOfExamination);

        for (Examination examination : examinations) {
            if (examination.getExaminationAppointment().getStartDate().isAfter(now))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            else
            {
                examination.setType(null);
                this.examinationRepository.save(examination);
            }
        }

        this.typeOfExaminationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public TypeOfExamination findByName(String type) {
        return this.typeOfExaminationRepository.findByName(type);
    }

    public ResponseEntity<List<TypeOfExaminationDTO>> getByClinicId(Long id) {
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(id);
        if (!optionalClinic.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Clinic clinic = optionalClinic.get();
        List<TypeOfExamination> list = this.typeOfExaminationRepository.findByClinic(clinic);

        List<TypeOfExaminationDTO> listDTO = new ArrayList<>();
        for (TypeOfExamination t : list) {
            listDTO.add(TypeOfExaminationDTO.builder()
                    .id(t.getId())
                    .name(t.getName())
                    .description(t.getDescription())
                    .build());
        }

        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }
}
