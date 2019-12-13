package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ClinicCenterAdministratorDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClinicCenterAdministrator extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logFirstTime", nullable = false)
    private boolean logFirstTime;
}