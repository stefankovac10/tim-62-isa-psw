package com.ProjectCC.dero.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long doctorId;

    @Column
    private Long patientId;

    @Column
    private Long typeId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private ExaminationAppointment examinationAppointment;
}
