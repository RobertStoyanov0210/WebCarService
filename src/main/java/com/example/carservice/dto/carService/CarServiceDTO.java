package com.example.carservice.dto.carService;

import com.example.carservice.data.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarServiceDTO {
    private long id;
    private String name;
    private int maxRepairingCars;

    private List<Qualification> serviceQualifications;

    private Set<Repair> vehiclesInRepair;

    private List<Brand> supportedBrands;

    private List<User> serviceEmployees;

    private Set<CarServicePricelist> pricelist;
}
