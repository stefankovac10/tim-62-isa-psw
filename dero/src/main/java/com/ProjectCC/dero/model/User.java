package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
   private String name;
   private String surname;
   private String jmbg;
   private String username;
   private String password;
   private String email;
   private String address;
   private String city;
   private String country;
   private String telephone;

   public User() {
   }
}