package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.OperationRoomDTO;
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
public class OperationRoom extends Room {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany(mappedBy = "operationRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Operation> operations;

//   public OperationRoom() {
//   }
//
//   public OperationRoom(OperationRoomDTO operationRoom) {
//      this.id = operationRoom.getId();
//      this.name = operationRoom.getName();
//      this.number = operationRoom.getNumber();
//   }
}