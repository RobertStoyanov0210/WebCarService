package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PricelistKey implements Serializable {
    @Column(name = "car_service_id")
    Long carServiceId;

    @Column(name = "qualification_id")
    Long qualificationId;
}
