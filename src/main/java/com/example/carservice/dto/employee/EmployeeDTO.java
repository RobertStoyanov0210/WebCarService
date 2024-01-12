package com.example.carservice.dto.employee;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.Role;
import com.example.carservice.dto.qualification.QualificationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private boolean isAccountNonExpired;
    @Column
    private boolean isAccountNonLocked;
    @Column
    private boolean isCredentialsNonExpired;
    @Column
    private boolean isEnabled;

    private CarService carService;
    private Set<Role> authorities;
    private Set<Qualification> qualifications;
}
