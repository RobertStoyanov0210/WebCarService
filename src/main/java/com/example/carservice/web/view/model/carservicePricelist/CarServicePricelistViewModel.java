package com.example.carservice.web.view.model.carservicePricelist;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarServicePricelistViewModel {
    private long id;
    private CarService carService;
    private Qualification qualification;
    private double price;
}
