package com.example.carservice.data.repository;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RepairRepository extends JpaRepository<Repair,Long> {
//    Set<Repair> findRepairByCarServiceEqualsAndCompleteIsTrue(CarService carService);
//    Set<Repair> findRepairsByCarServiceEqualsAndIsCompleteIsFalse(long carService);
    @Query("select r from Repair r where r.carService.id = ?1 and r.isComplete = false")
    Set<Repair> findCurrentRepairsByCarService(long id);

    @Query("select r from Repair r where r.customer.id = ?1 and r.isComplete = true")
    Set<Repair> findPastRepairsByUser(long id);

    @Query("select r from Repair r where r.carService.id = ?1 and r.isComplete = true")
    Set<Repair> findPastRepairsByCarService(long id);
}
