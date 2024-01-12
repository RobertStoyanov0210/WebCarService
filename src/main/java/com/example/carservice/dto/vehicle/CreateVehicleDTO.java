package com.example.carservice.dto.vehicle;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateVehicleDTO {
    @NotNull
    private Brand brand;
    @NotBlank
    private String model;
    private int productionYear;
    @NotNull
    private User owner;
}
