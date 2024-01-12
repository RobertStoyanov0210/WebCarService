package com.example.carservice.dto.carService;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.Repair;
import com.example.carservice.data.entity.User;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateCarServiceDTO {
    @NotBlank
    private String name;
    @NotNull
    private int maxRepairingCars;

    private List<Brand> supportedBrands;

}
