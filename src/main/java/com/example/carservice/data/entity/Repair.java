package com.example.carservice.data.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "repair")
public class Repair extends BaseEntity{

    @ManyToOne
    @ToString.Exclude
    private CarService carService;
    //vehicle's id
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    //List<>qualifications (какви услуги са ѝ направени - какво е направено на колата)
    @ManyToMany
    @JoinTable(
            name = "repair_services",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private Set<Qualification> plannedFixes;
    //user customerID
    @ManyToOne
    private User customer;
    //sum
    private double sum = 0.0;
    //isComplete
    private boolean isComplete = false;
    //date - will be set when isComplete is TRUE
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate = LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishDate;

}
