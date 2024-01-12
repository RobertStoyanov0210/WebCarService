package com.example.carservice.dto.customer;

import com.example.carservice.data.entity.Repair;
import com.example.carservice.data.entity.Role;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateCustomerDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    private String matchingPassword;

    private Set<Role> authorities;
    // No because he won't need them
//    @ToString.Exclude
//    private List<Qualification> qualifications;
    // No because he won't need them
    //    @ToString.Exclude
//    private CarService carService;

    @ToString.Exclude
    private Set<Vehicle> myVehicles = new HashSet<>();
    @ToString.Exclude
    private Set<Repair> myRepairs = new HashSet<>();
}
