package com.example.carservice.dto.carServicePricelist;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateCarServicePricelistDTO {
    @NotNull
    private CarService carService;
    @NotNull
    private Qualification qualification;
    @NotNull
    private double price;

}
