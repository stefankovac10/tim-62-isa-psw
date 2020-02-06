package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExaminationRoom extends Room {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany(mappedBy = "examinationRoom", fetch = FetchType.LAZY)
   private Set<Examination> examinations;

   @OneToMany(mappedBy = "examinationRoom", fetch = FetchType.LAZY)
   private Set<ExaminationAppointment> examinationAppointment;

}