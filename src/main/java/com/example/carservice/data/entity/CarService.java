package com.example.carservice.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "carService")
public class CarService extends BaseEntity{
    @NotBlank
    private String name;
    @NotNull
    private int maxRepairingCars;
    //TODO add current int, otherwise I need to fetch all repairs and then filter them every time

    @ManyToMany
    @JoinTable(
            name = "service_qualificaions",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private Set<Qualification> serviceQualifications = new HashSet<>();

//    private List<Vehicle> or List<Repair> vehiclesInRepair;
    @OneToMany(mappedBy = "carService",fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Repair> vehiclesInRepair = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "service_brands",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    private Set<Brand> supportedBrands = new HashSet<>();

    @OneToMany(mappedBy = "carService")
    private Set<User> serviceEmployees = new HashSet<>();

    //TODO other thing connected with reservation
    @OneToMany(mappedBy = "carService")
    @ToString.Exclude
    private Set<Reservation> reservations = new HashSet<>();

    //PRICELIST
    @OneToMany(mappedBy = "carService")
    private Set<CarServicePricelist> pricelist = new HashSet<>();
}
