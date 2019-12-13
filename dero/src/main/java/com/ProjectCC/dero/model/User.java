package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.UserDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "firstName", nullable = false)
   private String firstName;

   @Column(name = "lastName", nullable = false)
   private String lastName;

   @Column(name = "jmbg", unique = true, nullable = false)
   private String jmbg;

   @Column(name = "password", nullable = false)
   private String password;

   @Column(name = "email", unique = true, nullable = false)
   private String email;

   @Column(name = "address", nullable = false)
   private String address;

   @Column(name = "city", nullable = false)
   private String city;

   @Column(name = "country", nullable = false)
   private String country;

   @Column(name = "telephone", unique = true, nullable = false)
   private String telephone;

   /*@OneToOne(mappedBy = "user")
   public RegistrationRequest registrationRequest;*/
}