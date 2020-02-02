package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.ExaminationAppointment;
import com.ProjectCC.dero.model.ExaminationRequest;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.ExaminationRepository;
import com.ProjectCC.dero.repository.ExaminationRequestRepository;
import com.ProjectCC.dero.repository.PatientRepository;
import org.joda.time.Duration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationRequestService {

    private ExaminationRequestRepository examinationRequestRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ExaminationRequestService(ExaminationRequestRepository examinationRequestRepository, DoctorRepository doctorRepository,
                                     PatientRepository patientRepository, ModelMapper modelMapper) {
        this.examinationRequestRepository = examinationRequestRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<Void> save(ExaminationRequestDTO examinationRequestDTO) {
        ExaminationRequest examinationRequest = this.modelMapper.map(examinationRequestDTO, ExaminationRequest.class);
        ExaminationAppointment examinationAppointment = new ExaminationAppointment();
        examinationAppointment.setStartDate(examinationRequestDTO.getDate());
        examinationAppointment.setDuration(new Duration(examinationRequestDTO.getDuration()));
        examinationAppointment.setExaminationRequest(examinationRequest);
        Doctor doc = this.doctorRepository.findById(examinationRequestDTO.getDoctorId()).orElseGet(null);
        if (doc != null) {
            examinationAppointment.setClinic(doc.getClinic());
            examinationRequest.setExaminationAppointment(examinationAppointment);
            this.examinationRequestRepository.save(examinationRequest);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<ExaminationRequestDetailsDTO>> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<ExaminationRequest> requests = this.examinationRequestRepository.findAll(pageable);

        ArrayList<ExaminationRequestDetailsDTO> requestDTOS = new ArrayList<>();
        for (ExaminationRequest er : requests) {
            Optional<Doctor> doc = this.doctorRepository.findById(er.getDoctorId());
            Optional<Patient> pat = this.patientRepository.findById(er.getPatientId());
            DoctorDTO doctorDTO = null;
            PatientDTO patientDTO = null;
            if (doc.isPresent() && pat.isPresent()) {
                doctorDTO = DoctorDTO.builder().id(doc.get().getId())
                        .firstName(doc.get().getFirstName()).lastName(doc.get().getLastName()).build();
                patientDTO = PatientDTO.builder().id(pat.get().getId()).firstName(pat.get().getFirstName())
                        .lastName(pat.get().getLastName()).build();
            }
            requestDTOS.add(ExaminationRequestDetailsDTO.builder()
                .doctor(doctorDTO)
                .patient(patientDTO)
                .id(er.getId())
                .date(er.getExaminationAppointment().getStartDate())
                .duration(er.getExaminationAppointment().getDuration())
                .pages(requests.getTotalPages()).build());
        }
        return new ResponseEntity<>(requestDTOS, HttpStatus.OK);
    }
}
