package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    //List<DoctorDTO> findAllByFirstName(String );

    @Query("select firstName, lastName from Doctor d where d.firstName = ?1 and d.lastName = ?2 and d.email = ?3 and d.city = ?4 and d.country = ?5")
    List<Doctor> pronadjiDoktorePoImenuMejluGraduDrzavi(String firstName, String lastName, String email, String city, String country);

    Doctor findByEmail(String email);
}
