package com.example.carservice.web.view.model.employee;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.Role;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.web.view.model.carService.CarServiceViewModel;
import com.example.carservice.web.view.model.qualification.QualificationViewModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateEmployeeViewModel {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String matchingPassword;

    @ToString.Exclude
    private CarService carService;
    @ToString.Exclude
    private Set<Role> authorities;
    @ToString.Exclude
    private Set<Qualification> qualifications;
}
