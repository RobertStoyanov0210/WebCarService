package com.example.carservice.dto.repair;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateRepairDTO {
    @NotNull
    private CarService carService;
    @NotNull
    private Vehicle vehicle;
    private Set<Qualification> plannedFixes;
    @NotNull
    private User customer;
    private double sum;
    @NotNull
    private boolean isComplete;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishDate;
}
