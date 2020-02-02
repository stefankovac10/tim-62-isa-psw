package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminationService {

    private ExaminationRepository examinationRepository;
    private DiagnosisRepository diagnosisRepository;
    private ModelMapper modelMapper;
    private MedicationRepository medicationRepository;
    private DoctorRepository doctorRepository;
    private NurseRepository nurseRepository;
    private UserRepository userRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private ClinicRepository clinicRepository;

    @Autowired
    public ExaminationService(MedicalRecordRepository medicalRecordRepository, ModelMapper modelMapper,
                              ExaminationRepository examinationRepository, DiagnosisRepository diagnosisRepository,
                              MedicationRepository medicationRepository, DoctorRepository doctorRepository,
                              NurseRepository nurseRepository, UserRepository userRepository, ClinicRepository clinicRepository) {
        this.examinationRepository = examinationRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.medicationRepository = medicationRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.nurseRepository = nurseRepository;
        this.modelMapper = modelMapper;
        this.medicalRecordRepository = medicalRecordRepository;
        this.clinicRepository = clinicRepository;
    }

    public void save(ExaminationDTO examinationDTO) {

        Set<Medication> medications = new HashSet<>();
        for(MedicationDTO med: examinationDTO.getPrescription().getMedication()){
            medications.add(modelMapper.map(med, Medication.class));
        }

        Prescription prescription = Prescription.builder()
                                    .medication(medications)
                                    .certified(false)
                                    .build();

        MedicalRecord medicalRecord = medicalRecordRepository.getOne((long)1);

        Examination examination = Examination.builder()
                                    .report(examinationDTO.getReport())
                                    .prescription(prescription)
                                    .diagnosis(modelMapper.map(examinationDTO.getDiagnosis(), Diagnosis.class))
                                    .medicalRecord(medicalRecord)
                                    .build();

        examinationRepository.save(examination);
    }

    public void addExaminationReport(ExaminationDTO examinationDTO){
        Set<Medication> medications = new HashSet<>();
        for(MedicationDTO med: examinationDTO.getPrescription().getMedication()){
            medications.add(modelMapper.map(med, Medication.class));
        }

        Prescription prescription = Prescription.builder()
                                .medication(medications)
                                .certified(false)
                                .build();

        Examination examination = examinationRepository.findById(examinationDTO.getId()).orElseGet(null);
        examination.setPrescription(prescription);
        examination.setDiagnosis(modelMapper.map(examinationDTO.getDiagnosis(), Diagnosis.class));
        examination.setReport(examinationDTO.getReport());

        examinationRepository.save(examination);
    }

    public ExaminationDTO edit(ExaminationDTO examinationDTO) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String name = authentication.getName();
       User user = userRepository.findByEmail(name);

       Examination examination = examinationRepository.findById(examinationDTO.getId()).orElseGet(null);

       if(user.getId() == examination.getDoctor().getId()){
           examination.setReport(examinationDTO.getReport());

           Diagnosis diagnosis = diagnosisRepository.findById(examinationDTO.getDiagnosis().getId()).orElseGet(null);
           examination.setDiagnosis(diagnosis);

           examinationRepository.save(examination);
           return examinationDTO;
       }
       return null;
    }

    public ExaminationDTO getOne(Long id) {
        Examination e = examinationRepository.getOne(id);
        ExaminationRoomDTO examRoom = ExaminationRoomDTO.builder()
                                        .id(e.getExaminationRoom().getId())
                                        .name(e.getExaminationRoom().getName())
                                        .number(e.getExaminationRoom().getNumber())
                                        .build();
        ExaminationDTO examinationDTO =ExaminationDTO.builder()
                                        .duration(e.getExaminationAppointment().getDuration())
                                        .id(e.getId())
                                        .report(e.getReport())
                                        .discount(e.getDiscount())
                                        .examinationRoom(examRoom)
                                        .date(e.getExaminationAppointment().getStartDate())
                                        .type(TypeOfExaminationDTO.builder()
                                                .name(e.getType().getName()).build())
                                        .patient(PatientDTO.builder()
                                                .firstName(e.getPatient().getFirstName())
                                                .lastName(e.getPatient().getLastName())
                                                .build())
                                        .build();
        return examinationDTO;
    }
//    date: this.start,
//    type: this.type,
//    price: this.price,
//    examinationRoom: this.examinationRoom,
//    doctor: this.doctor
    public void addNewQuick(ExaminationDTO examinationDTO) {
        Examination examination = this.modelMapper.map(examinationDTO, Examination.class);
        Optional<Clinic> opt = this.clinicRepository.findById((long) 1);
        opt.ifPresent(examination::setClinic);
        this.examinationRepository.save(examination);
    }

    public List<ExaminationDTO> findDocExamination( String email,String role) {
        User user  = userRepository.findByEmail(email);
        List<Examination> examinations =  new ArrayList<>();
        if(role.equals("ROLE_DOCTOR")){
            examinations = examinationRepository.findDocExamination(user.getId());
        }else if(role.equals("ROLE_NURSE")){
            examinations = examinationRepository.findNurseExamination(user.getId());
        }

        List<ExaminationDTO> examinationDTOS = new ArrayList<>();

        for(Examination e: examinations){
            ExaminationRoomDTO examRoom = ExaminationRoomDTO.builder()
                                            .id(e.getExaminationRoom().getId())
                                            .name(e.getExaminationRoom().getName())
                                            .number(e.getExaminationRoom().getNumber())
                                            .build();
            examinationDTOS.add(ExaminationDTO.builder()
                                                .duration(e.getExaminationAppointment().getDuration())
                                                .id(e.getId())
                                                .report(e.getReport())
                                                .discount(e.getDiscount())
                                                .examinationRoom(examRoom)
                                                .date(e.getExaminationAppointment().getStartDate())
                                                .date(e.getExaminationAppointment().getStartDate())
                                                .type(TypeOfExaminationDTO.builder()
                                                        .name(e.getType().getName()).build())
                                                .patient(PatientDTO.builder()
                                                        .firstName(e.getPatient().getFirstName())
                                                        .lastName(e.getPatient().getLastName())
                                                        .build())
                                                .build());
        }

        return examinationDTOS;
    }

    public boolean check(Long id) {
        Examination examination = examinationRepository.findById(id).orElseGet(null);
        if(examination !=null){
            DateTime dateTime = examination.getExaminationAppointment().getStartDate();
            DateTime now = DateTime.now(DateTimeZone.UTC);
            Minutes duration = examination.getExaminationAppointment().getDuration().toStandardMinutes();
            if(now.isAfter(dateTime.minusMinutes(5)) && now.isBefore(dateTime.plus(duration))){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
