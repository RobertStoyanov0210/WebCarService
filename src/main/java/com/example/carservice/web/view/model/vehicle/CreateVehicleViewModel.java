package com.example.carservice.web.view.model.vehicle;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateVehicleViewModel {
    @NotNull
    private Brand brand;
    @NotBlank
    private String model;
    private int productionYear;
    private User owner;
}
