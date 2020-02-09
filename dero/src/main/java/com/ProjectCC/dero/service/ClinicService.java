package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ClinicService {

    private ModelMapper modelMapper;
    private ExaminationRepository examinationRepository;
    private ClinicRepository clinicRepository;
    private PrescriptionRepository prescriptionRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;

    @Autowired
    public ClinicService(ModelMapper modelMapper, ExaminationRepository examinationRepository,
                         ClinicRepository clinicRepository,
                         PrescriptionRepository prescriptionRepository,
                         DoctorRepository doctorRepository,
                         AppointmentRepository appointmentRepository) {
        this.modelMapper = modelMapper;
        this.examinationRepository = examinationRepository;
        this.clinicRepository = clinicRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public ResponseEntity<List<ClinicDTO>> getAll() {
        List<Clinic> clinics = clinicRepository.findAll();
        List<ClinicDTO> clinicDTOS = new ArrayList<>();

        for (Clinic c : clinics) {
            ClinicDTO clinicDTO  = ClinicDTO.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .address(c.getAddress())
                    .description(c.getDescription())
                    .grade(c.getGrade())
                    .income(c.getIncome())
                    .build();
            clinicDTOS.add(clinicDTO);
        }

        return new ResponseEntity<>(clinicDTOS, HttpStatus.OK);
    }

    public ResponseEntity<List<ClinicDTO>> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC,"name"));
        Page<Clinic> clinics = clinicRepository.findAll(pageable);
        List<ClinicDTO> clinicDTOS = new ArrayList<>();

        for (Clinic c : clinics) {
            ClinicDTO clinicDTO  = ClinicDTO.builder()
                                    .id(c.getId())
                                    .name(c.getName())
                                    .address(c.getAddress())
                                    .description(c.getDescription())
                                    .grade(c.getGrade())
                                    .income(c.getIncome())
                                    .pages(clinics.getTotalPages())
                                    .build();
            clinicDTOS.add(clinicDTO);
        }

        return new ResponseEntity<>(clinicDTOS, HttpStatus.OK);
    }

    public ClinicDTO save(ClinicDTO clinicDTO){
        Clinic clinic = Clinic.builder()
                        .address(clinicDTO.getAddress())
                        .income((double) 0)
                        .grade((double) 0)
                        .name(clinicDTO.getName())
                        .description(clinicDTO.getDescription())
                        .priceList("")
                        .build();

        Clinic clinic_find = clinicRepository.findByName(clinic.getName());
        if(clinic_find == null){
           clinic = clinicRepository.save(clinic);
            return modelMapper.map(clinic, ClinicDTO.class);
        }else{
            return null;
        }
    }

    public ResponseEntity<Void> remove(Long id){
        Clinic clinic = clinicRepository.findById(id).orElseGet(null);

        if(clinic == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            clinicRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    public Clinic findOne(Long id) {
        return clinicRepository.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<ClinicDTO> update(ClinicDTO clinicDTO) {
        Optional<Clinic> clinic_find = clinicRepository.findById(clinicDTO.getId());
        if (!clinic_find.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Clinic clinic = clinic_find.get();
        clinic.setName(clinicDTO.getName());
        clinic.setAddress(clinicDTO.getAddress());
        clinic.setDescription(clinicDTO.getDescription());
        this.clinicRepository.save(clinic);
        return new ResponseEntity<>(clinicDTO, HttpStatus.OK);
    }

    public ResponseEntity<ClinicDTO> findById(Long id) {

        Optional<Clinic> opt = clinicRepository.findById(id);
        if (!opt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Clinic clinic = opt.get();

        List<MedicalStaffDTO> staff = new ArrayList<>();
        for (MedicalStaff s : clinic.getMedicalStaff()) {
            String type;
            if (s instanceof Doctor) {
                type = "doctor";
            } else type = "nurse";
            staff.add(MedicalStaffDTO.builder()
                    .firstName(s.getFirstName())
                    .lastName(s.getLastName())
                    .address(s.getAddress())
                    .city(s.getCity())
                    .country(s.getCountry())
                    .email(s.getEmail())
                    .id(s.getId())
                    .jmbg(s.getJmbg())
                    .telephone(s.getTelephone())
                    .type(type)
                    .build());
        }

        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : clinic.getRooms()) {
            roomDTOS.add(RoomDTO.builder()
            .name(r.getName())
            .number(r.getNumber())
            .id(r.getId())
            .build());
        }

        List<ExaminationDTO> examinationDTOS = new ArrayList();
        for (Examination examination : clinic.getExaminations()) {
            if (examination.getPatient() == null) {
                examinationDTOS.add(ExaminationDTO.builder()
                .id(examination.getId())
                .date(examination.getExaminationAppointment().getStartDate())
                .duration(examination.getExaminationAppointment().getDuration())
                .doctor(DoctorDTO.builder().firstName(examination.getDoctor().getFirstName()).lastName(examination.getDoctor().getLastName()).build())
                .price(examination.getPrice()).build());
            }
        }

        ClinicDTO dto = ClinicDTO.builder()
                .name(clinic.getName())
                .description(clinic.getDescription())
                .address(clinic.getAddress())
                .id(clinic.getId())
                .medicalStaff(staff)
                .rooms(roomDTOS)
                .examinations(examinationDTOS)
                .build();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<List<ClinicDTO>> searchClinics(ClinicDTO clinicDTO){
        String name = "%" + clinicDTO.getName() + "%";
        String address = "%" + clinicDTO.getAddress() + "%";
        String description = "%" + clinicDTO.getDescription() + "%";

        List<Clinic> clinics = clinicRepository.searchClinics(name, address, description);
        List<ClinicDTO> clinicsDTO = new ArrayList<>();

        for (Clinic c : clinics) {
            clinicsDTO.add(ClinicDTO.builder()
                .name(c.getName())
                .description(c.getDescription())
                .id(c.getId())
                .address(c.getAddress())
                .build());
        }

        return new ResponseEntity<>(clinicsDTO, HttpStatus.OK);
    }

    public ResponseEntity<ClinicDTO> businessReport(Long id) {
        Optional<Clinic> opt = this.clinicRepository.findById(id);
        Clinic clinic = opt.get();
        ArrayList<MedicalStaffDTO> docs = new ArrayList<MedicalStaffDTO>();
        for (MedicalStaff d : clinic.getMedicalStaff()) {
            if (d instanceof Doctor) {
                docs.add(DoctorDTO.builder()
                .id(d.getId())
                .firstName(d.getFirstName())
                .lastName(d.getLastName())
                .grade(((Doctor) d).getGrade()).build());
            }
        }

        Optional<List<Examination>> opts = Optional.of(this.examinationRepository.findAll());
        List<Examination> examinations = opts.get();
        ArrayList<ExaminationDTO> exams = new ArrayList<>();
        for (Examination e : examinations) {
            if (e.getClinic().equals(clinic)) {
                exams.add(ExaminationDTO.builder()
                        .date(e.getExaminationAppointment().getStartDate())
                        .build());
            }
        }
        // Get examinations
        ClinicDTO clinicDTO = ClinicDTO.builder()
                .grade(clinic.getGrade())
                .medicalStaff(docs)
                .income(clinic.getIncome())
                .examinations(exams)
                .build();
        return new ResponseEntity<>(clinicDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<ClinicWithSpecilizedTypeDTO>> searchClinics2(String date, String type){
        List<Clinic> clinics = clinicRepository.findAll();
        List<ClinicWithSpecilizedTypeDTO> clinicDTOS = new ArrayList<>();

        DateTime startDate = DateTime.parse(date).plusHours(7);
        DateTime endDate = DateTime.parse(date).plusHours(19);

        HashMap<Doctor, List<ExaminationAppointment>> doctorsAppointments = new HashMap<>();

        for (Clinic c : clinics) {
            List<Examination> examinations = examinationRepository.findByClinicAndDate(c, startDate, endDate);
            List<Doctor> doctors = doctorRepository.findByClinic(c);

            for (Examination e : examinations) {
                if (doctorsAppointments.containsKey(e.getDoctor())) {
                    doctorsAppointments.get(e.getDoctor()).add(e.getExaminationAppointment());
                }
                else {
                    doctorsAppointments.put(e.getDoctor(), new ArrayList<>());
                    doctorsAppointments.get(e.getDoctor()).add(e.getExaminationAppointment());
                }
            }

            // sortirati liste appointmenta za svakog doktora
            /* dodati mozda hashmapu<Clinic, HashMap> koja sadrzi
            ove hashmape da ne bi neke hashmape sortirali vise puta */
            Set<Doctor> keys = doctorsAppointments.keySet();
            for (Doctor key : keys) {
                doctorsAppointments.get(key).sort((ea1, ea2) -> {
                    if (ea1.getStartDate() == null || ea2.getStartDate() == null)
                        return 0;
                    return ea1.getStartDate().compareTo(ea2.getStartDate());
                });
            }

            for (Doctor d : doctors) {
                if (d.getSpecialisedType().getName().equals(type) && isDoctorAbleToPerformAnExamination(doctorsAppointments.get(d), startDate, endDate)) {
                    clinicDTOS.add(ClinicWithSpecilizedTypeDTO.builder()
                            .name(c.getName())
                            .description(c.getDescription())
                            .id(c.getId())
                            .address(c.getAddress())
                            .specializedTypeID(d.getSpecialisedType().getId())
                            .build());
                    break;  // Bitno je da klinika ima bar jednog doktora koji može da obavi pregled
                }
            }
        }

        return new ResponseEntity<>(clinicDTOS, HttpStatus.OK);
    }

    /* - parametar examinationAppointmentList je lista sortiranih ExaminationAppointmenta
         u jednom danu
       - parametar startDate je pocetak radnog vremena tog dana
       - parametar endDate je kraj radnog vremena tog dana */
    public boolean isDoctorAbleToPerformAnExamination(List<ExaminationAppointment> examinationAppointmentList, DateTime startDate, DateTime endDate) {
        if (examinationAppointmentList == null) {
            return true;
        }

        if (examinationAppointmentList.isEmpty())
            return true;

        DateTime start = startDate;
        DateTime startEnd = start.plusMinutes(15);

        // proveravamo da li mozemo da uguramo 15 minuta negde
        DateTime tmp;
        for (int i = 0; i < examinationAppointmentList.size(); i++) {
            tmp = start;

            if (examinationAppointmentList.size() == 1) {   // i je i prvi i poslednji
                /* ako doktor ima samo jedan examinationAppointment,
                   uvek možemo da uguramo 15min između 7:00 i 19:00 */
                return true;
            } else if (i == 0) {    // dalje vazi n != 1
                if (tmp.plusMinutes(15).isBefore(examinationAppointmentList.get(i).getStartDate())) {
                    return true;
                }
                // start = ea[i].endDate + ea[i].duration
                start = examinationAppointmentList.get(i).getStartDate().plusMinutes((int) examinationAppointmentList.get(i).getDuration().getStandardMinutes());
            } else if (i == examinationAppointmentList.size() - 1) {    // i == size - 1
                if (tmp.plusMinutes(15).isBefore(examinationAppointmentList.get(i).getStartDate())
                    || examinationAppointmentList.get(i).getStartDate().plusMinutes((int) examinationAppointmentList.get(i).getDuration().getStandardMinutes()).isBefore(endDate)) {
                    return true;
                }
                return false;
            } else { // za sve i koji nisu 0 ili n
                if (tmp.plusMinutes(15).isBefore(examinationAppointmentList.get(i).getStartDate())){
                    return true;
                }
                start = examinationAppointmentList.get(i).getStartDate().plusMinutes((int) examinationAppointmentList.get(i).getDuration().getStandardMinutes());
            }
        }

        return false;   // ne bi, svakako, nikad trebalo da dodje do ove linije
    }
}
