package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "carservice_pricelist")
// ,
//        uniqueConstraints =
//                //other constraints
//        @UniqueConstraint(name = "UniquePricelists", columnNames = { "car_service_id", "qualification_id" }))
public class CarServicePricelist{
    @EmbeddedId
    PricelistKey id;

    @NotNull
    @ManyToOne(optional = false)
    @MapsId("carServiceId")
    private CarService carService;
    @NotNull
    @ManyToOne(optional = false)
    @MapsId("qualificationId")
    private Qualification qualification;
    @NotNull
    private double price;

}
