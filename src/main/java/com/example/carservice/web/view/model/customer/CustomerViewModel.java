package com.example.carservice.web.view.model.customer;

import com.example.carservice.data.entity.Repair;
import com.example.carservice.data.entity.Role;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CustomerViewModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;

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
