package com.example.carservice.services;

import com.example.carservice.dto.reservation.CreateReservationDTO;
import com.example.carservice.dto.reservation.ReservationDTO;
import com.example.carservice.dto.reservation.UpdateReservationDTO;
import com.example.carservice.web.view.model.reservation.ReservationViewModel;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Set;

public interface ReservationService {
    void createReservation(CreateReservationDTO createReservationDTO);
    Set<ReservationDTO> getCarServicesActiveReservation(@Min(1) long id);
    ReservationDTO getReservationByCarServiceAndVehicleAndDate(@Min(1) long carServiceId, @Min(1) long vehicleId, LocalDate date);

    void closeReservation(UpdateReservationDTO updateReservationDTO);

    void cancelReservation(ReservationDTO reservationDTO);
}
