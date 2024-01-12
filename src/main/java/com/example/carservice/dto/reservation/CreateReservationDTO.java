package com.example.carservice.dto.reservation;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateReservationDTO {
    @NotNull
    private CarService carService;
    @NotNull
    private Vehicle vehicle;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean isComplete;
}
