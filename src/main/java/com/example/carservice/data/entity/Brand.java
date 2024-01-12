package com.example.carservice.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "brand")
public class Brand extends BaseEntity {
    @NotBlank
    private String name;

    //TODO make it only one way available (service -> brand, not brand -> service)
    @ManyToMany(mappedBy = "supportedBrands")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CarService> servicesSupporting = new HashSet<>();
}
