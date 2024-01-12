package com.example.carservice.data.repository;

import com.example.carservice.data.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> getVehiclesByOwnerIdEquals(long id);
}