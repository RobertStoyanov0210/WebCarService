package com.example.carservice.web.view.model.carService;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.CarServicePricelist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateCarServiceViewModel {
    @NotBlank
    private String name;
    @NotNull
    private int maxRepairingCars;

    private List<Brand> supportedBrands;
    private Set<CarServicePricelist> pricelist;

}
