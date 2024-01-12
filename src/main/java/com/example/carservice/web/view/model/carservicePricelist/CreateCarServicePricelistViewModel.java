package com.example.carservice.web.view.model.carservicePricelist;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
public class CreateCarServicePricelistViewModel {
    @NotNull
    private CarService carService;
    @NotNull
    private Qualification qualification;
    @NotNull
    //TODO make zero unacceptable
    @PositiveOrZero
    private double price;
}
