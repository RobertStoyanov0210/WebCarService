package com.example.carservice.web.view.model.carService;

import com.example.carservice.data.entity.*;
import com.example.carservice.web.view.model.qualification.QualificationViewModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CarServiceViewModel {
    private long id;
    private String name;
    private int maxRepairingCars;

    @ToString.Exclude
    private List<QualificationViewModel> serviceQualifications;
    @ToString.Exclude
    private Set<Repair> vehiclesInRepair;
    @ToString.Exclude
    private List<Brand> supportedBrands;
    @ToString.Exclude
    private List<User> serviceEmployees;
    @ToString.Exclude
    private Set<CarServicePricelist> pricelist;

}
