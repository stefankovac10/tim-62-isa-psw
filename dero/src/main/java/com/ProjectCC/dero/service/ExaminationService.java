package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.exceptions.UserNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminationService {

    private ExaminationRepository examinationRepository;
    private DiagnosisRepository diagnosisRepository;
    private ModelMapper modelMapper;
    private ExaminationAppointmentRepository examinationAppointmentRepository;
    private MedicationRepository medicationRepository;
    private DoctorRepository doctorRepository;
    private NurseRepository nurseRepository;
    private UserRepository userRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private ClinicRepository clinicRepository;
    private PatientRepository patientRepository;

    @Autowired
    public ExaminationService(MedicalRecordRepository medicalRecordRepository, ModelMapper modelMapper, ExaminationAppointmentRepository examinationAppointmentRepository,
                              ExaminationRepository examinationRepository, DiagnosisRepository diagnosisRepository,
                              MedicationRepository medicationRepository, DoctorRepository doctorRepository,
                              NurseRepository nurseRepository, UserRepository userRepository, ClinicRepository clinicRepository,
                              PatientRepository patientRepository) {
        this.examinationRepository = examinationRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.medicationRepository = medicationRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.nurseRepository = nurseRepository;
        this.modelMapper = modelMapper;
        this.examinationAppointmentRepository = examinationAppointmentRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.clinicRepository = clinicRepository;
        this.patientRepository = patientRepository;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Doctor doctor = doctorRepository.findByEmail(username);
        Prescription prescription = Prescription.builder()
                                .medication(medications)
                                .doctor(doctor)
                                .certified(false)
                                .build();

        Examination examination = examinationRepository.findById(examinationDTO.getId()).orElseGet(null);
        examination.setPrescription(prescription);
        Clinic clinic = clinicRepository.findById(doctor.getClinic().getId()).orElseGet(null);
        if(examination.getPrice() == null){
            clinic.setIncome(clinic.getIncome() + Integer.parseInt(examinationDTO.getPrice()));
        }else{
            clinic.setIncome(clinic.getIncome() + Integer.parseInt(examination.getPrice()));
        }

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
        ExaminationRoomDTO examRoom;
        if(e.getExaminationRoom() ==  null){
            examRoom =  new ExaminationRoomDTO();
            examRoom.setName("");
            examRoom.setNumber(0);
        }else {
            examRoom = ExaminationRoomDTO.builder()
                    .id(e.getExaminationRoom().getId())
                    .name(e.getExaminationRoom().getName())
                    .number(e.getExaminationRoom().getNumber())
                    .build();
        }
        ExaminationDTO examinationDTO =ExaminationDTO.builder()
                                        .duration(e.getExaminationAppointment().getDuration())
                                        .id(e.getId())
                                        .report(e.getReport())
                                        .price(e.getPrice())
                                        .discount(e.getDiscount())
                                        .examinationRoom(examRoom)
                                        .date(e.getExaminationAppointment().getStartDate())
                                        .type(TypeOfExaminationDTO.builder()
                                                .name(e.getType().getName()).build())
                                        .patient(PatientDTO.builder()
                                                .id(e.getPatient().getId())
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
        ExaminationAppointment examinationAppointment = ExaminationAppointment.builder()
                .startDate(examinationDTO.getDate())
                .duration(new Duration(examinationDTO.getDuration()))
                .examinationRoom(examination.getExaminationRoom())
                .clinic(examination.getClinic())
                .build();
        examination.setExaminationAppointment(examinationAppointment);
        Optional<Clinic> opt = this.clinicRepository.findById((long) 1);
        opt.ifPresent(examination::setClinic);
        this.examinationRepository.save(examination);
        examinationAppointment.setExamination(examination);
        this.examinationAppointmentRepository.save(examinationAppointment);
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
            ExaminationRoomDTO examRoom;
            if(e.getExaminationRoom() == null){
                examRoom =  new ExaminationRoomDTO();
                examRoom.setName("");
                examRoom.setNumber(0);
            }else {
                examRoom = ExaminationRoomDTO.builder()
                        .id(e.getExaminationRoom().getId())
                        .name(e.getExaminationRoom().getName())
                        .number(e.getExaminationRoom().getNumber())
                        .build();
            }
            examinationDTOS.add(ExaminationDTO.builder()
                                                .duration(e.getExaminationAppointment().getDuration())
                                                .id(e.getId())
                                                .report(e.getReport())
                                                .discount(e.getDiscount())
                                                .price(e.getPrice())
                                                .examinationRoom(examRoom)
                                                .date(e.getExaminationAppointment().getStartDate())
                                                .date(e.getExaminationAppointment().getStartDate())
                                                .type(TypeOfExaminationDTO.builder()
                                                        .name(e.getType().getName()).build())
                                                .patient(PatientDTO.builder()
                                                        .id(e.getPatient().getId())
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
            if(dateTime.isBefore(now.plusMinutes(5))){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public ResponseEntity<List<ExaminationDTO>> getEximinationsByDoctorsID(Long doctorID, String date) {
        Doctor doctor = this.doctorRepository.findById(doctorID).orElseThrow(UserNotFoundException::new);

        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        DateTime startDate = DateTime.parse(date).plusHours(7);
        DateTime endDate = DateTime.parse(date).plusHours(19);

        List<Examination> examinations = this.examinationRepository.findByDoctorAndDate(doctor, startDate, endDate);
        List<ExaminationDTO> examinationDTOS = new ArrayList<>();
        for (Examination e : examinations) {
            examinationDTOS.add(ExaminationDTO.builder()
                    .duration(e.getExaminationAppointment().getDuration())
                    .id(e.getId())
                    .report(e.getReport())
                    .discount(e.getDiscount())
                    .date(e.getExaminationAppointment().getStartDate())
                    .doctor(DoctorDTO.builder()
                            .id(e.getDoctor().getId())
                            .firstName(e.getDoctor().getFirstName())
                            .lastName(e.getDoctor().getLastName())
                            .build())
                    .clinic(ClinicDTO.builder()
                            .id(e.getClinic().getId())
                            .name(e.getClinic().getName())
                            .build())
                    .appointment(AppointmentDTO.builder()
                            .startDate(e.getExaminationAppointment().getStartDate())
                            .duration(e.getExaminationAppointment().getDuration().getStandardMinutes()) // note that this is in minutes
                            .build())
                    .build());
        }

        return new ResponseEntity<>(examinationDTOS, HttpStatus.OK);
    }
}
