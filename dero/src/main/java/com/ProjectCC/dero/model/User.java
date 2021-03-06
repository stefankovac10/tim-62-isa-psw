package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.UserDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

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

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "user_authority",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
   private List<Authority> authorities;

   @Column(name = "enabled")
   private boolean enabled;

   @Column(name = "last_password_reset_date")
   private Timestamp lastPasswordResetDate;

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

   public User(UserDTO userDTO){
      this(userDTO.getFirstName(),userDTO.getLastName(),userDTO.getJmbg(), userDTO.getPassword(),userDTO.getEmail(),
              userDTO.getAddress(), userDTO.getCity(), userDTO.getCountry(), userDTO.getTelephone());
   }

    @Override
    public String getUsername() {
        return email;   // kod njih je username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}