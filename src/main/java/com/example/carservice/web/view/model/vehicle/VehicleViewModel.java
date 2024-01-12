package com.example.carservice.web.view.model.vehicle;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.Repair;
import com.example.carservice.data.entity.Reservation;
import com.example.carservice.data.entity.User;
import com.example.carservice.web.view.model.brand.BrandViewModel;
import com.example.carservice.web.view.model.repair.RepairViewModel;
import com.example.carservice.web.view.model.reservation.ReservationViewModel;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
public class VehicleViewModel {
    private long id;
    private BrandViewModel brand;
    private String model;
    private int productionYear;
    private User owner;
    @ToString.Exclude
    private Set<Reservation> reservations = new HashSet<>();
    @ToString.Exclude
    private Set<Repair> repairs = new HashSet<>();

}
