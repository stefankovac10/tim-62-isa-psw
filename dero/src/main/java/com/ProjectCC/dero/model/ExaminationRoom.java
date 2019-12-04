package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationRoom extends Room {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "appointmentList", nullable = false)
   private int appointmentList;

   @OneToMany(mappedBy = "examinationRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Examination> examinations;

//   public ExaminationRoom() {
//   }

}