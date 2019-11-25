package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.OperationRoomDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class OperationRoom {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "number", nullable = false)
   private int number;
   @Column(name = "name", nullable = false)
   private String name;

   @OneToMany(mappedBy = "operationRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Operation> operations;

   public OperationRoom() {
   }

   public OperationRoom(OperationRoomDTO operationRoom) {
      this.id = operationRoom.getId();
      this.name = operationRoom.getName();
      this.number = operationRoom.getNumber();
   }
}