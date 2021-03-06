package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    //List<DoctorDTO> findAllByFirstName(String );

    /*
    @Query("select firstName, lastName from Doctor d where d.firstName = ?1 and d.lastName = ?2 and d.email = ?3 and d.city = ?4 and d.country = ?5")
    List<Doctor> searchDoctors(String firstName, String lastName, String email, String city, String country);
    */

    @Query("from Doctor d where lower(d.firstName) like lower(?1) and lower(d.lastName) like lower(?2) and lower(d.email) like lower(?3) and lower(d.city) like lower(?4) and lower(d.country) like lower(?5) and lower(d.clinic.name) like lower(?6)")
    List<Doctor> searchDoctors(String firstName, String lastName, String email, String city, String country, String clinic);

    Doctor findByEmail(String email);

    @Query("select d.operations from Doctor d where d.id = ?1")
    List<Operation> findDocOperation(Long id);


    @Query("select d from Doctor d where d.clinic.id = ?1")
    List<Doctor> findAllByClinic(Long id);

    List<Doctor> findByClinic(Clinic clinic);

}
