package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Reservation;
import com.example.carservice.data.repository.ReservationRepository;
import com.example.carservice.dto.reservation.CreateReservationDTO;
import com.example.carservice.dto.reservation.ReservationDTO;
import com.example.carservice.dto.reservation.UpdateReservationDTO;
import com.example.carservice.services.ReservationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createReservation(CreateReservationDTO createReservationDTO) {
        System.out.println("DTOOO");
//        System.out.println(createReservationDTO);
        System.out.println("ENTITY");
//        System.out.println(modelMapper.map(createReservationDTO,Reservation.class));

        //TODO eventually
//        TypeMap<CreateReservationDTO, Reservation> propertyMapper = this.modelMapper.createTypeMap(CreateReservationDTO.class, Reservation.class);
//        propertyMapper.addMapping(CreateReservationDTO::getDate, Reservation::s);
        Reservation reservation = modelMapper.map(createReservationDTO,Reservation.class);
        reservation.getId().setDate(createReservationDTO.getDate());
        reservationRepository.save(reservation);
    }

    @Override
    public Set<ReservationDTO> getCarServicesActiveReservation(long id) {
        return reservationRepository.findCurrentReservationsByCarService(id).stream()
                .map(r -> modelMapper.map(r,ReservationDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ReservationDTO getReservationByCarServiceAndVehicleAndDate(long carServiceId, long vehicleId, LocalDate date) {
        return modelMapper.map(reservationRepository.findSpecificReservation(carServiceId, vehicleId,date),ReservationDTO.class);
    }

    @Override
    public void closeReservation(UpdateReservationDTO updateReservationDTO) {
        updateReservationDTO.setComplete(true);
        System.out.println("IN SERVICE");
        System.out.println(updateReservationDTO);
        Reservation reservation = modelMapper.map(updateReservationDTO,Reservation.class);
        System.out.println(reservation);
        reservationRepository.save(modelMapper.map(updateReservationDTO,Reservation.class));
        //status change
    }

    @Override
    public void cancelReservation(ReservationDTO reservationDTO) {
        System.out.println("CANCEL RESERVATION");
//        System.out.println(reservationDTO);
        Reservation reservation = modelMapper.map(reservationDTO,Reservation.class);
//        System.out.println(reservation);
        reservationRepository.delete(reservation);
    }
}
