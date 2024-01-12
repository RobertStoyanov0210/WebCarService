package com.example.carservice.services;

import com.example.carservice.dto.vehicle.CreateVehicleDTO;
import com.example.carservice.dto.vehicle.VehicleDto;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public interface VehicleService {
    List<VehicleDto> getCustomerVehicles(Authentication authentication);
    VehicleDto getCustomerVehicle(Authentication authentication, long id);
    VehicleDto getVehicleById(@Min(1) long id);
    void createVehicle(@Valid CreateVehicleDTO map);
}
