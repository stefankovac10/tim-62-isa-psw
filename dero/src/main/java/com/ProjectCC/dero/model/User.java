package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_table")
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

   @OneToOne(mappedBy = "user")
   public RegistrationRequest registrationRequest;

   @Autowired
   public User() {
   }

   @Autowired
   public User(String firstName, String lastName, String jmbg,
               String password, String email, String address, String city,
               String country, String telephone) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.jmbg = jmbg;
      this.password = password;
      this.email = email;
      this.address = address;
      this.city = city;
      this.country = country;
      this.telephone = telephone;
   }
}