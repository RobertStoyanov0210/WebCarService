package com.example.carservice.dto.vehicle;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.Repair;
import com.example.carservice.data.entity.Reservation;
import com.example.carservice.data.entity.User;
import com.example.carservice.dto.reservation.ReservationDTO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class VehicleDto {
    private long id;
    private Brand brand;
    private String model;
    private int productionYear;
    private User owner;
    @ToString.Exclude
    private Set<ReservationDTO> reservations = new HashSet<>();
    @ToString.Exclude
    private Set<Repair> repairs = new HashSet<>();
}
