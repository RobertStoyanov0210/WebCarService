package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "reservation")
public class Reservation{
    @EmbeddedId
    private ReservationKey id;

    @NotNull
    @ManyToOne
    @MapsId("carServiceId")
    @JoinColumn(name = "car_service_id")
    private CarService carService;
    @NotNull
    @ManyToOne
    @MapsId("vehicleId")
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

//    @NotNull
//    @MapsId("date")
//    @JoinColumn(name = "date")
//    private LocalDate date;

    private boolean isComplete = false;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", carService=" + carService.getId() +
                ", vehicle=" + vehicle.getId() +
                ", isComplete=" + isComplete +
                '}';
    }
}
