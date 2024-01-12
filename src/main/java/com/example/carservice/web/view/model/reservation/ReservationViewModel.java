package com.example.carservice.web.view.model.reservation;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.ReservationKey;
import com.example.carservice.data.entity.Vehicle;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationViewModel {
    private ReservationKey id;
    private long carServiceId;
    private String carServiceName;
    private long vehicleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean isComplete;
}
