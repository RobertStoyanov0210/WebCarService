package com.example.carservice.web.view.model.repair;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateRepairViewModel {
    @NotNull
    private CarService carService;
    @NotNull
    private Vehicle vehicle;
    @NotEmpty
    private Set<Qualification> plannedFixes;
    @NotNull
    private User customer;
    private double sum;
    @NotNull
    private boolean isComplete;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate = LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishDate;

    @Override
    public String toString() {
        return "CreateRepairViewModel{" +
                "carService=" + carService.getId() +
                ", vehicle=" + vehicle.getId() +
                ", plannedFixes=" + plannedFixes +
                ", customer=" + customer.getId() +
                ", sum=" + sum +
                ", isComplete=" + isComplete +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
