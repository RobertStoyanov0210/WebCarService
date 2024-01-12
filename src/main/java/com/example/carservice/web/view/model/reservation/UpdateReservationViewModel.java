package com.example.carservice.web.view.model.reservation;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UpdateReservationViewModel {
    @NotNull
    private CarService carService;
    @NotNull
    private Vehicle vehicle;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message="The date has to be in the future!")
    private LocalDate date;
    private boolean isComplete;
}
