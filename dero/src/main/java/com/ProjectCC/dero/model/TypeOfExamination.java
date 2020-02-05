package com.ProjectCC.dero.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "specialisedType")
    private Set<Doctor> specialisedDoctors;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Examination> examinations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    public Clinic clinic;

}
