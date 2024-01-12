package com.example.carservice.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "qualification")
public class Qualification extends BaseEntity {
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "qualifications")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> qualifiedEmployees;

    @ManyToMany(mappedBy = "serviceQualifications")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CarService> serviceQualifications;

    @ManyToMany(mappedBy = "plannedFixes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Repair> serviceRepairs;

    @OneToMany(mappedBy = "qualification")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CarServicePricelist> pricelists = new HashSet<>();
}
