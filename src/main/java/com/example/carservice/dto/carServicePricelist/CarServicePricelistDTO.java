package com.example.carservice.dto.carServicePricelist;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CarServicePricelistDTO {
    private long id;
    private CarService carService;
    private Qualification qualification;
    private double price;

}
