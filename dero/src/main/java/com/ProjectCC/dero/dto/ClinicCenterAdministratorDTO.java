package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.ClinicCenterAdministrator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicCenterAdministratorDTO extends  UserDTO{
    private Long id;
    private boolean logFirstTime;
}
