package com.example.carservice.data.repository;

import com.example.carservice.data.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("select r from Reservation r where r.carService.id = ?1 and r.isComplete = false")
    Set<Reservation> findCurrentReservationsByCarService(long id);

    @Query("select r from Reservation r where r.id.carServiceId = ?1 and r.id.vehicleId = ?2 and r.id.date = ?3")
    Reservation findSpecificReservation(long carServiceId, long vehicleId, LocalDate date);
}
