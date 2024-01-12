package com.example.carservice.web.view.model.carService;

import com.example.carservice.data.entity.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateCarServiceViewModel {
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private int maxRepairingCars;

    private List<Brand> supportedBrands;
}
