package com.example.carservice.web.view.model.repair;

import com.example.carservice.web.view.model.reservation.ReservationViewModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CreateRepairFromReservationViewModel {
    @Valid
    @NotNull
    private CreateRepairViewModel createRepairViewModel;
    private long carServiceId;
    private long vehicleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
