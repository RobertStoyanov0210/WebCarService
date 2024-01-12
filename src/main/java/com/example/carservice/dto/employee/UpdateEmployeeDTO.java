package com.example.carservice.dto.employee;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.Role;
import com.example.carservice.dto.qualification.QualificationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateEmployeeDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String matchingPassword;

    @NotNull
    private CarService carService;
    private Set<Role> authorities;
    private Set<Qualification> qualifications;
}
