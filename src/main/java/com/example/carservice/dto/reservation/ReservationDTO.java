package com.example.carservice.dto.reservation;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.ReservationKey;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReservationDTO {
    private ReservationKey id;
    private CarService carService;
    private Vehicle vehicle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean isComplete;

}
