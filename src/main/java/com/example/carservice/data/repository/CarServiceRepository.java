package com.example.carservice.data.repository;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarServiceRepository extends JpaRepository<CarService,Long> {
    List<CarService> getCarServicesBySupportedBrandsContains(Brand brand);
}
