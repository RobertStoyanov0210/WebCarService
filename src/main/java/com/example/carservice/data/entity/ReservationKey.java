package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@ToString
public class ReservationKey implements Serializable {

    @Column(name = "car_service_id")
    private long carServiceId;
    @Column(name = "vehicle_id")
    private long vehicleId;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message="The date has to be in the future!")
    private LocalDate date;
}
