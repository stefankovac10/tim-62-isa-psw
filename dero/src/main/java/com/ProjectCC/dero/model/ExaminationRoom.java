package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class ExaminationRoom {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "number", nullable = false)
   private int number;

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "appointmentList", nullable = false)
   private int appointmentList;

   @OneToMany(mappedBy = "examinationRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Examination> examinations;

   public ExaminationRoom() {
   }

    public ExaminationRoom(ExaminationRoomDTO examinationRoom) {
      this.name = examinationRoom.getName();
      this.number = examinationRoom.getNumber();
    }
}